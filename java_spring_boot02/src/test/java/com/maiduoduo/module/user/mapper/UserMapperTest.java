package com.maiduoduo.module.user.mapper;

import com.maiduoduo.module.user.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by ASUS on 2020/9/10 19:50
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testFindUserByUserId(){

        User user = userMapper.findUserByUserId(2L);
        System.out.println("user = " + user);
    }
}