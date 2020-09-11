package com.maiduoduo.module.userRole.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by ASUS on 2020/9/10 19:46
 */

@Data
@Table(name = "user_role")
public class UserRole implements Serializable {

    @Id
    private Long userId;
    @Id
    private Integer roleId;
}
