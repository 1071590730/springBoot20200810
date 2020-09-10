package com.wjb.javaSpringBoot.modules.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wjb.javaSpringBoot.config.ResourceConfigBean;
import com.wjb.javaSpringBoot.modules.account.dao.UserDao;
import com.wjb.javaSpringBoot.modules.account.dao.UserRoleDao;
import com.wjb.javaSpringBoot.modules.account.entity.Role;
import com.wjb.javaSpringBoot.modules.account.entity.User;
import com.wjb.javaSpringBoot.modules.account.service.UserService;
import com.wjb.javaSpringBoot.modules.common.vo.Result;
import com.wjb.javaSpringBoot.modules.common.vo.SearchVo;
import com.wjb.javaSpringBoot.utils.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by ASUS on 2020/8/20 13:32
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private ResourceConfigBean resourceConfigBean;

    @Override
    @Transactional
    public Result<User> insertUser(User user) {
        User userTemp = userDao.getUserByUserName(user.getUserName());
        if (userTemp != null) {
            return new Result<User>(
                    Result.ResultStatus.FAILD.status, "User name is repeat.");
        }

        user.setCreateDate(LocalDateTime.now());
        user.setPassword(MD5Util.getMD5(user.getPassword()));//MD5加密
        userDao.insertUser(user);

        userRoleDao.deleteUserRoleByUserId(user.getUserId());
        List<Role> roles = user.getRoles();
        if (roles != null && !roles.isEmpty()) {
            roles.stream().forEach(item -> {//遍历角色（jdk8+）
                userRoleDao.insertUserRole(user.getUserId(), item.getRoleId());
            });
        }

        return new Result<User>(
                Result.ResultStatus.SUCCESS.status, "Insert success.", user);
    }

    @Override
    public Result<User> login(User user) {
//        User userTemp = userDao.getUserByUserName(user.getUserName());
//        if (userTemp != null &&
//                userTemp.getPassword().equals(MD5Util.getMD5(user.getPassword()))) {
//            return new Result<>(Result.ResultStatus.SUCCESS.status, "Success.", userTemp);
//        }
//        return new Result<User>(Result.ResultStatus.FAILD.status, "UserName or password is error.");
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken =
                new UsernamePasswordToken(user.getAccountName(),
                        MD5Util.getMD5(user.getPassword()));//获取登陆账号密码做令牌
        usernamePasswordToken.setRememberMe(user.getRememberMe());//记住密码选中

        try {
            subject.login(usernamePasswordToken);//调用登陆方法，传入令牌（账号、密码）
            subject.checkRoles();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<User>(Result.ResultStatus.FAILD.status,
                    "UserName or password is error.");
        }

        Session session = subject.getSession();
        session.setAttribute("user", (User)subject.getPrincipal());

        return new Result<User>(Result.ResultStatus.SUCCESS.status,
                "Login success.", user);

    }

    @Override
    public PageInfo<User> getUsersBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo<User>(
                Optional.ofNullable(userDao.getUsersBySearchVo(searchVo))
                        .orElse(Collections.emptyList()));
    }

    @Override
    @Transactional
    public Result<User> updateUser(User user) {
        User userTemp = userDao.getUserByUserName(user.getUserName());
        if (userTemp != null && userTemp.getUserId() != user.getUserId()) {
            return new Result<User>(
                    Result.ResultStatus.FAILD.status, "User name is repeat.");
        }

        userDao.updateUser(user);

        userRoleDao.deleteUserRoleByUserId(user.getUserId());
        List<Role> roles = user.getRoles();
        if (roles != null && !roles.isEmpty()) {
            roles.stream().forEach(item -> {
                userRoleDao.insertUserRole(user.getUserId(), item.getRoleId());
            });
        }

        return new Result<User>(
                Result.ResultStatus.SUCCESS.status, "Update success.", user);
    }

    @Override
    @Transactional
    public Result<Object> deleteUser(int userId) {
        userDao.deleteUser(userId);
        userRoleDao.deleteUserRoleByUserId(userId);
        return new Result<>(Result.ResultStatus.SUCCESS.status, "Delete success.");
    }

    @Override
    public User getUserByUserId(int userId) {
        return userDao.getUserByUserId(userId);
    }

    @Override
    public Result<String> uploadUserImg(MultipartFile file) {
        if (file.isEmpty()) {
            return new Result<String>(
                    Result.ResultStatus.FAILD.status, "Please select img.");
        }

        String relativePath = "";
        String destFilePath = "";
        try {
            String osName = System.getProperty("os.name");
            if (osName.toLowerCase().startsWith("win")) {
                destFilePath = resourceConfigBean.getLocationPathForWindows() +
                        file.getOriginalFilename();
            } else {
                destFilePath = resourceConfigBean.getLocationPathForLinux()
                        + file.getOriginalFilename();
            }
            relativePath = resourceConfigBean.getRelativePath() +
                    file.getOriginalFilename();
            File destFile = new File(destFilePath);
            file.transferTo(destFile);

        } catch (IOException e) {
            e.printStackTrace();
            return new Result<String>(
                    Result.ResultStatus.FAILD.status, "Upload failed.");
        }

        return new Result<String>(
                Result.ResultStatus.SUCCESS.status, "Upload success.", relativePath);
    }

    @Override
    @Transactional
    public Result<User> updateUserProfile(User user) {
        User userTemp = userDao.getUserByUserName(user.getUserName());
        if (userTemp != null && userTemp.getUserId() != user.getUserId()) {
            return new Result<User>(Result.ResultStatus.FAILD.status, "User name is repeat.");
        }

        userDao.updateUser(user);

        return new Result<User>(Result.ResultStatus.SUCCESS.status, "Edit success.", user);
    }

    //用户名查询
    @Override
    public User getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }

    //退出系统
    @Override
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        Session session = subject.getSession();
        session.setAttribute("user", null);
    }
}