package com.maiduoduo.module.perm.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by ASUS on 2020/9/10 19:40
 */

@Data
@Table(name = "perm")
public class Perm implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer permId;
    private String permName;
    private String permCode;
    private String url;



}