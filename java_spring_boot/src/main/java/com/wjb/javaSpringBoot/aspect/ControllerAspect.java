package com.wjb.javaSpringBoot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by ASUS on 2020/8/17 15:47
 */
//切面程序
@Aspect
//注册为容器主键
@Component
public class ControllerAspect {
    private final static Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);
    /**
     * 关联在方法上的切点
     * 第一个*代表返回类型不限
     * 第二个*代表module下所有子包
     * 第三个*代表所有类
     * 第四个*代表所有方法
     * (..) 代表参数不限
     * Order 代表优先级，数字越小优先级越高
     */
//    设置切片（绑定位置）
    @Pointcut("execution(public * com.wjb.javaSpringBoot.modules.*.controller.*.*(..))")
    @Order(1)
    public void controllerPointCut() {}

    //前置
    @Before(value = "com.wjb.javaSpringBoot.aspect.ControllerAspect.controllerPointCut()")
    public void beforeController(JoinPoint joinpoint) {
        LOGGER.debug("==== This is before controller ====");
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        LOGGER.debug("请求来源：" + request.getRemoteAddr());
        LOGGER.debug("请求URL：" + request.getRequestURL().toString());
        LOGGER.debug("请求方式：" + request.getMethod());
        LOGGER.debug("响应方法：" +
                joinpoint.getSignature().getDeclaringTypeName() + "." +
                joinpoint.getSignature().getName());
        LOGGER.debug("请求参数：" + Arrays.toString(joinpoint.getArgs()));
    }

    //环绕
    @Around(value = "com.wjb.javaSpringBoot.aspect.ControllerAspect.controllerPointCut()")
    public Object aroundController(ProceedingJoinPoint proceedingJoinPoint)
            throws Throwable {
        LOGGER.debug("==== This is around controller ==== ");
        return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    }

    //结尾
    @After(value = "com.wjb.javaSpringBoot.aspect.ControllerAspect.controllerPointCut()")
    public void afterController() {
        LOGGER.debug("==== This is after controller ====");
    }
}
