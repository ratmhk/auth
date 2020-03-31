package com.config;

import com.entity.User;
import com.mapper.UserMapper;
import com.mapper.UserRoleMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 自定义权限匹配和账号密码匹配
 */
public class MyShiroRealm extends AuthorizingRealm {

    private  static final Logger log =  LoggerFactory.getLogger(AuthorizingRealm.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;


    @Override
    public String getName() { return "myShiroRealm"; }



    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String userInfo = (String)principals.getPrimaryPrincipal();

        List<Map<String, Object>> userRoles = userRoleMapper.getUserRoleByAcc(userInfo);

        for (int i = 0; i < userRoles.size(); i++) {
            Map<String,Object> map = userRoles.get(i);
            String roleName = (String) map.get("roleName");
            authorizationInfo.addRole(roleName);
        }
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的， 验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)  throws AuthenticationException {

        String username = (String) token.getPrincipal();
        log.info("登录认证的用户  "+username);

        User u  = userMapper.selectByAcc(username);
        if (u == null) return null;

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                username, //用户名
                u.getPassword(), //密码
                // ByteSource.Util.bytes(userInfo.getCredentialsSalt()),salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }


}
