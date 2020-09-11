package com.maiduoduo.config.shiro;

import com.maiduoduo.module.perm.entity.Perm;
import com.maiduoduo.module.role.entity.Role;
import com.maiduoduo.module.user.entity.User;
import com.maiduoduo.module.user.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author lykgogo
 * @Date 2020/9/10 09:07
 */

@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        User primaryPrincipal = (User) principals.getPrimaryPrincipal();
//        User user = userService.findUserByUserId(primaryPrincipal.getUserId());
//        List<Role> role = user.getRoles();
//        for (Role r:
//             role) {
//            info.addRole(r.getRoleName());
//            List<Perm> perms = r.getPerms();
//            for (Perm perm :
//                 perms) {
//                info.addStringPermission(perm.getPermName());
//            }
//        }
//        return info;

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        User user = (User) principals.getPrimaryPrincipal();

        User userResult= userService.findUserByUserId(user.getUserId());

        if (userResult.getRoles() != null){
            for (Role role : userResult.getRoles()) {

                info.addRole(role.getRoleName());
                if (role.getPerms() != null){
                    for (Perm perm : role.getPerms()) {
                        info.addStringPermission(perm.getPermCode());
                    }
                }
            }
        }
        
        return info;
    }

    //认证(登录)
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        User user = userService.findUserByUsername(username);
        if (user == null){
            throw new UnknownAccountException();
        }
        //交由Shiro判断密码

        return new SimpleAuthenticationInfo(user,user.getPassword(), ByteSource.Util.bytes(user.getSalt()),this.getName());
    }
}
