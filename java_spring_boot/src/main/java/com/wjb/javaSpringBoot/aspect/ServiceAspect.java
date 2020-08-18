package com.wjb.javaSpringBoot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by ASUS on 2020/8/17 16:32
 */
//切面程序
@Aspect
//注册为容器主键
@Component
public class ServiceAspect {

    private final static Logger LOGGER = LoggerFactory.getLogger(ServiceAspect.class);

    //指定切入位置
    @Pointcut("@annotation(com.wjb.javaSpringBoot.aspect.ServiceAnnotation)")
    @Order(2)//优先级
    public void servicePointCut(){}

    // @Before前置
    @Before(value = "com.wjb.javaSpringBoot.aspect.ServiceAspect.servicePointCut()")
    public void beforeService(JoinPoint joinpoint) {
        LOGGER.debug("==== This is beforeService service ==自定义aop==");
    }

    // @Around环绕
    @Around(value = "com.wjb.javaSpringBoot.aspect.ServiceAspect.servicePointCut()")
    public Object aroundService(ProceedingJoinPoint proceedingJoinPoint)
            throws Throwable {
        LOGGER.debug("==== This is aroundService service ==自定义aop== ");
        return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    }

    //  @After结尾
    @After(value = "com.wjb.javaSpringBoot.aspect.ServiceAspect.servicePointCut()")
    public void afterService() {
        LOGGER.debug("==== This is afterService service ==自定义aop==");
    }
}
