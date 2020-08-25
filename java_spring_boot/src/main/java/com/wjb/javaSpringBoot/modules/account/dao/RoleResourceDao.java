package com.wjb.javaSpringBoot.modules.account.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by ASUS on 2020/8/25 16:26
 */
@Mapper
@Repository
public interface RoleResourceDao {

    @Delete("delete from role_resource where resource_id = #{resourceId}")
    void deletRoleResourceByResourceId(int resourceId);

    @Insert("insert role_resource(role_id, resource_id) value(#{roleId}, #{resourceId})")
    void addRoleResource(@Param("roleId") int roleId, @Param("resourceId") int resourceId);

}