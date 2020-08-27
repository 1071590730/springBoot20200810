package com.wjb.springcloud.springCloudClientAccount.modules.account.controller;

import com.github.pagehelper.PageInfo;
import com.wjb.springcloud.springCloudClientAccount.modules.account.entity.User;
import com.wjb.springcloud.springCloudClientAccount.modules.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by ASUS on 2020/8/27 20:05
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 127.0.0.1:8762/api/user/3 ---- get
     */
    @GetMapping("/user/{userId}")
    public User getUserByUserId(@PathVariable int userId) {
        return userService.getUserByUserId(userId);
    }

}
