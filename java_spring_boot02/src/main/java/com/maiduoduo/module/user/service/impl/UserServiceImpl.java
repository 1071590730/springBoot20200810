package com.maiduoduo.module.user.service.impl;

import com.maiduoduo.module.user.entity.User;
import com.maiduoduo.module.user.mapper.UserMapper;
import com.maiduoduo.module.user.service.UserService;
import com.maiduoduo.utils.MailUtils;
import com.maiduoduo.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author lykgogo
 * @Date 2020/9/9 09:39
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(User user) {
        user.setStatus(0);

        String salt = SaltUtils.getSalt(10);
        String md5Pwd = new Md5Hash(user.getPassword(), salt, 1024).toHex();
        user.setSalt(salt);
        user.setPassword(md5Pwd);

        userMapper.insertSelective(user);
        MailUtils.sendMail(user.getEmail(), "<a href='http://localhost:8888/user/active/"+user.getUser_id()+"'>你正在注册xxxx系统，点击此链接以激活账户，若非本人注册请忽略。</a>", "激活邮件");
    }

//        user.setPassword(MD5Util.getMD5(user.getPassword()));//MD5加密
//    public void login(User user){
//        //登陆时，先将密码MD5加密，再和数据库已加密的密码比对
//        user.setPassword(MD5Util.getMD5(user.getPassword()));//MD5加密
//        userMapper.selectUserAndPassword(user);
//    }

    @Override
    public boolean isUsernameUsable(String username) {
        User user = new User();
        user.setUsername(username);
        int count = userMapper.selectCount(user);
        if (count == 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateStatusById(User user) {
        int count = userMapper.updateByPrimaryKeySelective(user);
        if (count > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public User findUserByUsername(String username) {
        User user = new User();
        user.setUsername(username);

        return userMapper.selectOne(user);
    }
}
