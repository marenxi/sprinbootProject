package com.itszt.manager;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContextLoggerListener implements ApplicationListener<ContextRefreshedEvent> {


    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) && @annotation(org.springframework.web.bind.annotation.ResponseBody)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object LoggerPrint(ProceedingJoinPoint point) throws Throwable {
        boolean isController = point.getTarget().getClass().isAnnotationPresent(Controller.class);
        boolean isRestController = point.getTarget().getClass().isAnnotationPresent(RestController.class);
        Signature signature1 = point.getSignature();
        MethodSignature signature = null;
        if (signature1 instanceof MethodSignature) {
            signature = (MethodSignature) signature1;
        }
        boolean hasRequestMapping = signature.getMethod().isAnnotationPresent(RequestMapping.class);
        if ((isRestController || isController) && hasRequestMapping) {
            if (RequestContextHolder.getRequestAttributes() instanceof ServletRequestAttributes) {
                ServletRequestAttributes servlet = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                HttpServletRequest request = servlet.getRequest();
                HttpServletResponse response = servlet.getResponse();
                Logger log = LoggerFactory.getLogger(point.getTarget().getClass());
                log.info("Class:" + point.getTarget().getClass().getName() + "Method:" + signature.getMethod().getName() + "Params:" + signature.getParameterNames() + "" + point.getArgs());
            }
        }
        return point.proceed();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

    }
}

