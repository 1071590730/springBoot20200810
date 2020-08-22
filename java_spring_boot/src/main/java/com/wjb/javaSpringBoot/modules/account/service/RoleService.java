package com.wjb.javaSpringBoot.modules.account.service;

import com.wjb.javaSpringBoot.modules.account.entity.Role;

import java.util.List;

/**
 * Created by ASUS on 2020/8/22 21:56
 */
public interface RoleService {

    List<Role> getRoles();
}
