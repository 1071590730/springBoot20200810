package com.wjb.javaSpringBoot.aspect;

import java.lang.annotation.*;

/**自定义一个注解ServiceAnnotation，以注解的方式指定切片
 * Created by ASUS on 2020/8/17 16:22
 */
//指定级别  ElementType.METHOD为方法 级别
@Target(ElementType.METHOD)
//文档形
@Documented
//运行环境 为运行时运行
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceAnnotation {
    //自定义注解属性、默认值
    String value() default "aaa";
}
