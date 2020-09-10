package com.maiduoduo.module.user.controller;

import com.maiduoduo.module.user.entity.User;
import com.maiduoduo.module.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ASUS on 2020/9/9 11:33
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 127.0.0.1:8888/user/login
     */
//    @RequestMapping("login")
//    public String login(){
//        return "login.html";
//    }

    /**
     * 127.0.0.1:8888/user/register
     */
    @PostMapping("/register")
    public String regiter(User user){
        userService.register(user);
        return "anon/register_success.html";
    }
    /**
     * 127.0.0.1:8888/user/toRegister
     */
    @GetMapping("/toRegister")
    public String toRegister(){
        return "anon/register.html";
    }

    @PostMapping("/isUsernameUsable")
    @ResponseBody
    public Map<String,Object> isUsernameUsable(String username){
        boolean flag = userService.isUsernameUsable(username);
        HashMap<String, Object> map = new HashMap<>();
        map.put("flag",flag);
        map.put("msg","查询用户是否可用成功！！！");
        return map;
    }

    @GetMapping("/active/{id}")
    @ResponseBody
    public String active(@PathVariable Long id) {
        User user = new User();
        user.setUser_id(id);
        user.setStatus(1);
        boolean flag = userService.updateStatusById(user);
        if (flag){
            return "<a href='http://localhost:8888/user/toLogin/'>激活成功，点击此链接登陆账户。</a>";
        }else {
            return "激活失败！！！";
        }
    }

    @PostMapping("login")
    public String login(User user, Model model){

        try {
            Subject subject = SecurityUtils.getSubject();
            subject.login(new UsernamePasswordToken(user.getUsername(),user.getPassword()));
            return "index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "登陆失败，用户名不存在");
        } catch (IncorrectCredentialsException e){
            model.addAttribute("msg", "登陆失败，密码错误");
        }

        return "anon/login";
    }

    /**
     * 127.0.0.1:8888/user/toLogin
     */
    @GetMapping("/toLogin")
    public String toLogin(){
        return "anon/login.html";
    }
}
