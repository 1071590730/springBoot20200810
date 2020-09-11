package com.maiduoduo.module.user.entity;

import com.maiduoduo.module.role.entity.Role;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * @Author lykgogo
 * @Date 2020/9/9 09:36
 */

@Data
@Table(name = "user")
public class User implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true)//回传id
    private Long userId;

    private String username;
    private String password;
    private String phone_number;
    private String email;
    private String salt;
    private String url;
    private Integer status;

    private List<Role> roles;
}
