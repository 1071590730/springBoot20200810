package com.maiduoduo.module.user.mapper;

import com.maiduoduo.module.user.entity.User;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by ASUS on 2020/9/10 14:33
 */

public interface UserMapper extends Mapper<User> {

    @Select("select * from user where user_id = #{userId}")
    @Results(id = "userResult",value = {
            @Result(property = "userId",column = "user_id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNumber",column = "phone_number"),
            @Result(property = "email",column = "email"),
            @Result(property = "salt",column = "salt"),
            @Result(property = "url",column = "url"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "user_id",javaType = List.class,
                    many = @Many(select = "com.maiduoduo.module.role.mapper.RoleMapper.findRoleByUserId")),
    })
    User findUserByUserId(Long userId);

}