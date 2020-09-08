package com.itszt.manager.controller;

import com.itszt.manager.entity.DataResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @RequestMapping("/doLogin")
    public DataResponse doLogin(String username,String password){
        //1.封装用户信息
        UsernamePasswordToken token=
                new UsernamePasswordToken(username,password);
        //2.提交token对象(传递给SecurityManager)
        //2.1获取subject对象
        Subject subject=
                SecurityUtils.getSubject();
        //2.2执行登录认证
        subject.login(token);
        return new DataResponse("login ok");
    }
}
