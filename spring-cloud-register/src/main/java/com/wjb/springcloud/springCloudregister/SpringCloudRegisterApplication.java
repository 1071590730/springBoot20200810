package com.wjb.springcloud.springCloudregister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
//开启注册中心
@EnableEurekaServer
public class SpringCloudRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudRegisterApplication.class, args);
    }

}
