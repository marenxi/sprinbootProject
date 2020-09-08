package com.itszt.manager.serviceImpl;

import com.itszt.manager.dao.UserDao;
import com.itszt.manager.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiroUserRealm extends AuthorizingRealm{


    @Autowired
    private UserDao userdao;

    public void setCredentialsMatcher(
            CredentialsMatcher credentialsMatcher) {
        //构建凭证匹配对象
        HashedCredentialsMatcher cMatcher=
                new HashedCredentialsMatcher();
        //设置加密算法
        cMatcher.setHashAlgorithmName("MD5");
        //设置加密次数
        cMatcher.setHashIterations(1);
        super.setCredentialsMatcher(cMatcher);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1.获取用户名(用户页面输入)
        UsernamePasswordToken upToken=
                (UsernamePasswordToken)token;
        String username=upToken.getUsername();
        //2.基于用户名查询用户信息
        User user=
                userdao.findUserByUserName(username);
        //3.判定用户是否存在
        if(user==null)
            throw new UnknownAccountException();
        //5.封装用户信息
        ByteSource credentialsSalt=
                ByteSource.Util.bytes(user.getSalt());
        //记住：构建什么对象要看方法的返回值
        SimpleAuthenticationInfo info=
                new SimpleAuthenticationInfo(
                        user,//principal (身份)
                        user.getPassword(),//hashedCredentials
                        credentialsSalt, //credentialsSalt
                        getName());//realName
        //6.返回封装结果
        return info;//返回值会传递给认证管理器(后续
        //认证管理器会通过此信息完成认证操作)

    }
}
