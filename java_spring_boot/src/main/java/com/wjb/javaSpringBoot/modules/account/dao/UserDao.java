package com.wjb.javaSpringBoot.modules.account.dao;

import com.wjb.javaSpringBoot.modules.account.entity.User;
import com.wjb.javaSpringBoot.modules.common.vo.SearchVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ASUS on 2020/8/20 13:33
 */
@Repository
@Mapper
public interface UserDao {

    @Insert("insert into user (user_name, password, user_img, create_date) " +
            "values (#{userName}, #{password}, #{userImg}, #{createDate})")
    @Options(useGeneratedKeys = true, keyColumn = "user_id", keyProperty = "userId")
    void insertUser(User user);

    @Select("select * from user where user_name = #{userName}")
    @ResultMap(value = "userResults")
    User getUserByUserName(String userName);

    @Select("<script>" +
            "select * from user "
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (user_name like '%${keyWord}%' ) "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='orderBy != \"\" and orderBy != null'>"
            + " order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise>"
            + " order by user_name desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<User> getUsersBySearchVo(SearchVo searchVo);

    @Update("update user set user_name = #{userName}, " +
            "user_img = #{userImg} where user_id = #{userId}")
    void updateUser(User user);

    @Delete("delete from user where user_id = #{userId}")
    void deleteUser(int userId);

    @Select("select * from user where user_id = #{userId}")
    @Results(id = "userResults", value = {
            @Result(column = "user_id", property = "userId"),
            @Result(column = "user_id", property = "roles",
                    javaType = List.class,
                    many = @Many(select = "com.wjb.javaSpringBoot.modules." +
                            "account.dao.RoleDao.getRolesByUserId"))
            }
    )
    User getUserByUserId(int userId);
}
