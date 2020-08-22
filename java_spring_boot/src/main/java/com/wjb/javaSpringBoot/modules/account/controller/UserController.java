package com.wjb.javaSpringBoot.modules.account.controller;

import com.github.pagehelper.PageInfo;
import com.wjb.javaSpringBoot.modules.account.entity.User;
import com.wjb.javaSpringBoot.modules.account.service.UserService;
import com.wjb.javaSpringBoot.modules.common.vo.Result;
import com.wjb.javaSpringBoot.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ASUS on 2020/8/20 13:29
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    /**登陆
     * 127.0.0.1/api/login ---- post
     * {"userName":"admin","password":"111111"}
     */
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> login(@RequestBody User user) {
        return userService.login(user);
    }

    /**添加用户
     * 127.0.0.1/api/user ---- post
     * {"userName":"admin","password":"111111"}
     */
    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> insertUser(@RequestBody User user) {
        return userService.insertUser(user);
    }

    /**用户列表
     * 127.0.0.1/api/users --- post
     * {"currentPage":"1","pageSize":"5","keyWord":"wa","orderBy":"user_name","sort":"desc"}
     */
    @PostMapping(value = "/users",consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<User> getUsersBySearchVo(@RequestBody SearchVo searchVo) {
        return userService.getUsersBySearchVo(searchVo);
    }
    /**更新用户
     * 127.0.0.1/api/user ---- put
     * {"userName":"wa","userImg":"/aaa.jpg","userId":"4"}
     */
    @PutMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    /**删除用户
     * 127.0.0.1/api/user/1 ---- delete
     */
    @DeleteMapping("/user/{userId}")
    public Result<Object> deleteUser(@PathVariable int userId) {
        return userService.deleteUser(userId);
    }

    /**获取用户信息
     * 127.0.0.1/api/user/1 ---- get
     */
    @GetMapping("/user/{userId}")
    public User getUserByUserId(@PathVariable int userId) {
        return userService.getUserByUserId(userId);
    }
}