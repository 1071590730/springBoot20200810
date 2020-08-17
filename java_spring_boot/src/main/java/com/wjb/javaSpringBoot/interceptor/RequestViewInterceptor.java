package com.wjb.javaSpringBoot.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ASUS on 2020/8/17 11:37
 */
//注册为主键 （可以调用系统其他bean）
@Component
public class RequestViewInterceptor implements HandlerInterceptor {

    private final static Logger LOGGER = LoggerFactory.getLogger(RequestViewInterceptor.class);

    //preHandle拦截点之前
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        LOGGER.debug("==== Pre interceptor ==preHandle拦截点之前==");
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    //postHandle拦截点内
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LOGGER.debug("==== Post interceptor ==postHandle拦截点内==");
        // TODo
        //判断接口的值
        if (modelAndView == null || modelAndView.getViewName().startsWith("redirect")) {
            return;
        }

        String path = request.getServletPath();//获取path路径
        String template = (String) modelAndView.getModelMap().get("template");//获取是否已经设置template值
        if (StringUtils.isBlank(template)) {//判断为空
            if (path.startsWith("/")) {//去‘/’
                path = path.substring(1);
            }
            modelAndView.getModelMap().addAttribute(
                    "template", path.toLowerCase());//设置路径并 转小写（path不规范）
        }

        HandlerInterceptor.super.preHandle(request, response, handler);
    }

    //afterCompletion拦截之后
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOGGER.debug("==== After interceptor ==afterCompletion拦截之后==");
        HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
