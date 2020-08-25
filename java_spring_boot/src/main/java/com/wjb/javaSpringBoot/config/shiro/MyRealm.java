package com.wjb.javaSpringBoot.config.shiro;

import com.wjb.javaSpringBoot.modules.account.entity.Resource;
import com.wjb.javaSpringBoot.modules.account.entity.Role;
import com.wjb.javaSpringBoot.modules.account.entity.User;
import com.wjb.javaSpringBoot.modules.account.service.ResourceService;
import com.wjb.javaSpringBoot.modules.account.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ASUS on 2020/8/25 13:56
 */
@Component
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private ResourceService resourceService;

    //资源授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principals.getPrimaryPrincipal();
        List<Role> roles = user.getRoles();
        if (roles != null && !roles.isEmpty()) {
            roles.stream().forEach(item -> {
                simpleAuthorizationInfo.addRole(item.getRoleName());
                List<Resource> resources =
                        resourceService.getResourcesByRoleId(item.getRoleId());
                if (resources != null && !resources.isEmpty()) {
                    resources.stream().forEach(resource -> {
                        simpleAuthorizationInfo.addStringPermission(resource.getPermission());
                    });
                }
            });
        }

        return simpleAuthorizationInfo;
    }

    //身份认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取前端登陆的用户信息
        String userName = (String) token.getPrincipal();
        //数据库查询用户信息
        User user = userService.getUserByUserName(userName);
        if (user == null) {
            throw new UnknownAccountException("用户不存在。");
        }
        //返回（用户，用户密码，用户名(唯一标识)）
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }
}
