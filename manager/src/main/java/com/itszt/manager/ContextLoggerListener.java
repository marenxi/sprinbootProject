package com.itszt.manager;


import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


@Order(value=999) //切面的优先级
@Component
@Aspect
public class ContextLoggerListener implements ApplicationListener<ContextRefreshedEvent> {


    /**
     * @Pointcut：定义一个切点，后面跟随一个表达式，表达式可以定义为某个 package 下的方法，也可以是自定义注解等；
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) && @annotation(org.springframework.web.bind.annotation.ResponseBody)")
    public void pointCut() {
    }

    /**
     * 切点定义好后，定义五种通知类型
     *
     * @Before: 在切点之前，织入相关代码；
     * @After: 在切点之后，织入相关代码;
     * @AfterReturning: 在切点返回内容后，织入相关代码，一般用于对返回值做些加工处理的场景；
     * @AfterThrowing: 用来处理当织入的代码抛出异常后的逻辑处理;
     * @Around: 在切入点前后织入代码，并且可以自由的控制何时执行切点；
     */
    @Around("pointCut()")
    public Object LoggerPrint(ProceedingJoinPoint joinPoint) throws Throwable {
        //方法执行结果
        Object result = null;
        //方法执行开始时间
        Long startTime = null;
        //方法执行结束时间
        Long endTime = null;
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        boolean isController = joinPoint.getTarget().getClass().isAnnotationPresent(Controller.class);
        boolean isRestController = joinPoint.getTarget().getClass().isAnnotationPresent(RestController.class);
        Signature signature1 = joinPoint.getSignature();
        MethodSignature signature = null;
        if (signature1 instanceof MethodSignature) {
            signature = (MethodSignature) signature1;
        }
        Method currentMethod = joinPoint.getTarget().getClass().getMethod(signature.getName(), signature.getParameterTypes());
        boolean hasRequestMapping = signature.getMethod().isAnnotationPresent(RequestMapping.class);
        if ((isRestController || isController) && hasRequestMapping) {
            if (RequestContextHolder.getRequestAttributes() instanceof ServletRequestAttributes) {
                ServletRequestAttributes servlet = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                HttpServletRequest request = servlet.getRequest();
                HttpServletResponse response = servlet.getResponse();
                String methodName = joinPoint.getSignature().getName();
                String param = getParam(joinPoint);
                // 开始打印请求日志
                logger.info("========================================== Start ==========================================");
                // 打印请求 url
                logger.info("URL            : {}", request.getRequestURL().toString());
                // 打印 Http method
                logger.info("HTTP Method    : {}", request.getMethod());
                // 打印调用 controller 的执行方法
                logger.info("Method         : {}", joinPoint.getSignature().getName());
                // 打印请求的 IP
                logger.info("IP             : {}", request.getRemoteAddr());
                // 打印请求入参
                logger.info("Request Args   : {}", new Gson().toJson(joinPoint.getArgs()));
                logger.info("方法:" + signature.getMethod().getName() + "-->参数:" + param);
                startTime = System.currentTimeMillis();
                result = joinPoint.proceed();
                endTime = System.currentTimeMillis();
                // 打印出参
                logger.info("Response Args  : {}", new Gson().toJson(result));
                String methodDeclar = getMethodNameAndFieldsTypeAndName(currentMethod);
                logger.info("methodDeclar   : {}",methodDeclar);
                // 结束打印请求日志
                logger.info("=========================================== End ===========================================");
            } else {
                startTime = System.currentTimeMillis();
                result = joinPoint.proceed();
                endTime = System.currentTimeMillis();
            }
        } else {
            startTime = System.currentTimeMillis();
            result = joinPoint.proceed();
            endTime = System.currentTimeMillis();
        }
        // 执行耗时
        logger.info("Time-Consuming : {} ms", endTime - startTime);
        return result;
    }

    public String getParam(ProceedingJoinPoint proceedingJoinPoint) {
        Map<String, Object> map = new HashMap<String, Object>();
        Object[] values = proceedingJoinPoint.getArgs();
        String[] names = ((CodeSignature) proceedingJoinPoint.getSignature()).getParameterNames();
        for (int i = 0; i < names.length; i++) {
            map.put(names[i], values[i]);
        }
        return new Gson().toJson(map);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

    }


    /**
     * 得到方法参数的名称
     *
     * @return
     * @throws NoSuchMethodException
     */
    private String getMethodNameAndFieldsTypeAndName(Method currentMethod) throws NoSuchMethodException {
        StringBuffer stringBuffer=new StringBuffer();
        // 得到方法的返回值类型的类类型
        Class<?> returnType = currentMethod.getReturnType();
        stringBuffer.append(returnType.getName());
        stringBuffer.append(" ");
        // 得到方法的名称
        stringBuffer.append(currentMethod.getName());
        stringBuffer.append("(");
        // 获取参数类型
        Class[] paramTypes = currentMethod.getParameterTypes();
        for (Class class2 : paramTypes) {
            stringBuffer.append(class2.getSimpleName());
            stringBuffer.append(",");
        }
        stringBuffer.append(")");
        return stringBuffer.toString();
    }

}

