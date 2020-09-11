package com.maiduoduo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan({"com.maiduoduo.module.user.mapper", "com.maiduoduo.module.role.mapper",
        "com.maiduoduo.module.perm.mapper", "com.maiduoduo.module.userRole.mapper"})
public class MaiDuoDuoApplication {
    public static void main(String[] args) {
        SpringApplication.run(MaiDuoDuoApplication.class, args);
    }
}
