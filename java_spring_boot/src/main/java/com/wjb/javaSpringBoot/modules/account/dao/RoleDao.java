package com.wjb.javaSpringBoot.modules.account.dao;

import com.wjb.javaSpringBoot.modules.account.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ASUS on 2020/8/22 21:54
 */
@Repository
@Mapper
public interface RoleDao {

    @Select("select * from role r " +
            "left join user_role ur on r.role_id = ur.role_id " +
            "where ur.user_id = #{userId}")
    List<Role> getRolesByUserId(int userId);

    @Select("select * from role")
    List<Role> getRoles();
}