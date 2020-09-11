package com.maiduoduo.module.perm.mapper;

import com.maiduoduo.module.perm.entity.Perm;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by ASUS on 2020/9/10 19:41
 */
public interface PermMapper extends Mapper<Perm> {

    @Select("SELECT\n" +
            "p.perm_id,\n" +
            "p.perm_name,\n" +
            "p.perm_code,\n" +
            "p.url\n" +
            "FROM\n" +
            "role_perm rp\n" +
            "INNER JOIN perm p ON rp.perm_id = p.perm_id\n" +
            "where rp.role_id=#{roleId}")
    @Results(id = "permResult",value = {
            @Result(property = "permId",column = "perm_id"),
            @Result(property = "permName",column = "perm_name"),
            @Result(property = "permCode",column = "perm_code"),
            @Result(property = "url",column = "url"),
    })
    Perm findPermByRole_id(Integer roleId);
}
