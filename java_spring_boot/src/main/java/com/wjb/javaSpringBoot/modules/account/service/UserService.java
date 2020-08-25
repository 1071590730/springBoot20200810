package com.wjb.javaSpringBoot.modules.account.service;

import com.github.pagehelper.PageInfo;
import com.wjb.javaSpringBoot.modules.account.entity.User;
import com.wjb.javaSpringBoot.modules.common.vo.Result;
import com.wjb.javaSpringBoot.modules.common.vo.SearchVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by ASUS on 2020/8/20 13:31
 */

public interface UserService {

    Result<User> insertUser(User user);

    Result<User> login(User user);

    PageInfo<User> getUsersBySearchVo(SearchVo searchVo);

    Result<User> updateUser(User user);

    Result<Object> deleteUser(int userId);

    User getUserByUserId(int userId);

    Result<String> uploadUserImg(MultipartFile file);

    Result<User> updateUserProfile(User user);

    User getUserByUserName(String userName);

    void logout();
}