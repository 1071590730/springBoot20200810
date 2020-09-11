package com.maiduoduo.module.role.entity;

import com.maiduoduo.module.perm.entity.Perm;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2020/9/10 19:42
 */

@Data
@Table(name = "role")
public class Role implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer roleId;
    private String roleName;
    private String description;

    private List<Perm> perms;
}
