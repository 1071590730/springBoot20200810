package com.wjb.javaSpringBoot.modules.account.service;

import com.github.pagehelper.PageInfo;
import com.wjb.javaSpringBoot.modules.account.entity.User;
import com.wjb.javaSpringBoot.modules.common.vo.Result;
import com.wjb.javaSpringBoot.modules.common.vo.SearchVo;
import com.wjb.javaSpringBoot.modules.test.entity.City;

import java.util.List;

/**
 * Created by ASUS on 2020/8/20 13:31
 */

public interface UserService {

    Result<User> insertUser(User user);

    Result<User> login(User user);

    PageInfo<User> getUsersBySearchVo(SearchVo searchVo);
}