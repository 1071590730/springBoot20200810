package com.maiduoduo.module.user.service;

import com.maiduoduo.module.user.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author lykgogo
 * @Date 2020/9/9 10:01
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;


    @Test
    public void testRegister(){
        User user = new User();
        user.setUsername("xiaochen");
        user.setPassword("123");

        userService.register(user);
    }


    @Test
    public void testIsUsernameRepeat(){
        Boolean flag = userService.isUsernameUsable("xiaochen22");
        System.out.println("flag = " + flag);
    }

}
