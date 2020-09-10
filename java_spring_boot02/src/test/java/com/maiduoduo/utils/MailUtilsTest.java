package com.maiduoduo.utils;

import org.junit.Test;

/**
 * @Author lykgogo
 * @Date 2020/9/9 15:27
 */

public class MailUtilsTest {


    @Test
    public void testSendMail(){
        MailUtils.sendMail("1071590730@qq.com", "这是一条测试邮件", "你好~~");
    }
}
