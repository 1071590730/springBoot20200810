package com.wjb.springcloud.springCloudClientAccount.modules.account.service;

import com.github.pagehelper.PageInfo;
import com.wjb.springcloud.springCloudClientAccount.modules.account.entity.User;
import com.wjb.springcloud.springCloudClientAccount.modules.common.vo.Result;
import com.wjb.springcloud.springCloudClientAccount.modules.common.vo.SearchVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by ASUS on 2020/8/27 20:02
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