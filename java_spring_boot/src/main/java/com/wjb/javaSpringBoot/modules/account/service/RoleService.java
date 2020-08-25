package com.wjb.javaSpringBoot.modules.account.service;

import com.github.pagehelper.PageInfo;
import com.wjb.javaSpringBoot.modules.account.entity.Role;
import com.wjb.javaSpringBoot.modules.common.vo.Result;
import com.wjb.javaSpringBoot.modules.common.vo.SearchVo;

import java.util.List;

/**
 * Created by ASUS on 2020/8/22 21:56
 */
public interface RoleService {

    List<Role> getRoles();

    Result<Role> editRole(Role role);

    Result<Role> deleteRole(int roleId);

    PageInfo<Role> getRoles(SearchVo searchVo);

    List<Role> getRolesByUserId(int userId);

    List<Role> getRolesByResourceId(int resourceId);

    Role getRoleById(int roleId);
}
