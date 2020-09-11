package com.maiduoduo.module.user.service;

import com.maiduoduo.module.user.entity.User;

/**
 * @Author lykgogo
 * @Date 2020/9/9 09:39
 */

public interface UserService {

    public void register(User user);

    boolean isUsernameUsable(String username);

//    boolean updateStatusById(User user);

    User findUserByUsername(String username);

    void active(Long id);

    User findUserByUserId(Long userId);

}
