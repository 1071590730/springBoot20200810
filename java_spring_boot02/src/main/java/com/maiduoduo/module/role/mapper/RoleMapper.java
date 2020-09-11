package com.maiduoduo.module.role.mapper;

import com.maiduoduo.module.role.entity.Role;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by ASUS on 2020/9/10 19:42
 */

public interface RoleMapper extends Mapper<Role> {

    @Select("SELECT\n" +
            "role.role_id,\n" +
            "role.role_name,\n" +
            "role.description\n" +
            "FROM\n" +
            "user_role\n" +
            "INNER JOIN role ON user_role.role_id = role.role_id\n" +
            "where user_role.user_id = #{userId}")
    @Results(id = "roleResult",value = {
            @Result(property = "roleId",column = "role_id"),
            @Result(property = "roleName",column = "role_name"),
            @Result(property = "description",column = "description"),
            @Result(property = "perms",column = "role_id",javaType = List.class,
                    many = @Many(select = "com.maiduoduo.module.perm.mapper.PermMapper.findPermByRole_id")),
    })
    Role findRoleByUserId(Long userId);
}