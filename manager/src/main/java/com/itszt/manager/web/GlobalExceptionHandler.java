package com.itszt.manager.web;


import com.itszt.manager.entity.DataResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 此注解修饰的类为一个全局异常处理类
 * 当控制层出现异常时:
 * 1)首先检测当前controller是否有异常处理方法
 * 2)其次检测全局异常处理类是否有异常处理方法
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * @ExceptionHandler 描述的方法为一个异常处理方法
     * @param e
     * @return
     */
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
    public DataResponse doHandleRuntimeException(
			RuntimeException e) {
    	e.printStackTrace();//控制台
        log.error(e.getMessage());
		return new DataResponse("");
	}
	
	@ExceptionHandler(ShiroException.class)
	@ResponseBody
	public DataResponse doHandleShiroException(
			ShiroException e) {
		DataResponse r=new DataResponse();
		r.setCode(0);
		if(e instanceof UnknownAccountException) {
			r.setMsg("账户不存在");
		}else if(e instanceof LockedAccountException) {
			r.setMsg("账户已被禁用");
		}else if(e instanceof IncorrectCredentialsException) {
			r.setMsg("密码不正确");
		}else if(e instanceof AuthorizationException) {
			r.setMsg("没有此操作权限");
		}else {
			r.setMsg("系统维护中");
		}
		e.printStackTrace();
		return r;
	}

	
	
	
	
	
	
	
}
