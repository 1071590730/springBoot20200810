package com.wjb.springcloud.springCloudClientTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//启动类注解
@EnableEurekaClient
public class SpringCloudClientTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudClientTestApplication.class, args);
    }

}
