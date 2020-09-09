package com.wjb.javaSpringBoot.config;

import com.wjb.javaSpringBoot.filter.RequestParameFilter;
import com.wjb.javaSpringBoot.interceptor.RequestViewInterceptor;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by ASUS on 2020/8/11 11:32
 */
//注释为 配置类
@Configuration
@AutoConfigureAfter({WebMvcAutoConfiguration.class})
public class WebMvcConfig  implements WebMvcConfigurer {

    @Value("${server.http.port}")
    private int httpPort;
    //注释 自定义的拦截器
    @Autowired
    private RequestViewInterceptor requestViewInterceptor;
    @Autowired
    private ResourceConfigBean resourceConfigBean;

    @Bean
    public Connector connector() {
        Connector connector = new Connector();
        connector.setPort(httpPort);
        connector.setScheme("http");
        return connector;
    }

    /**
     * 1、重新注册ServletWebServerFactory bean
     * 2、以TomcatServletWebServerFactory实现，添加http连接器
     * @return
     */
    @Bean
    public ServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(connector());
        return tomcat;
    }

    //注册过滤器
    @Bean
    public FilterRegistrationBean<RequestParameFilter> register() {
        FilterRegistrationBean<RequestParameFilter> register =
                new FilterRegistrationBean<RequestParameFilter>();
        register.setFilter(new RequestParameFilter());
        return register;
    }



    //InterceptorRegistry 拦截器的注册器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor()置为拦截器，并拦截所有("/**")
        registry.addInterceptor(requestViewInterceptor).addPathPatterns("/**");
    }


    //添加本地资源文件
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String osName = System.getProperty("os.name");//获取操作系统名字
        if (osName.startsWith("win")) {//判断如果是以“win”开通，为Windows系统
            registry.addResourceHandler(resourceConfigBean.getRelativePathPattern())
                    .addResourceLocations(ResourceUtils.FILE_URL_PREFIX +
                            resourceConfigBean.getLocationPathForWindows());
        } else {//linx
            registry.addResourceHandler(resourceConfigBean.getRelativePathPattern())
                    .addResourceLocations(ResourceUtils.FILE_URL_PREFIX +
                            resourceConfigBean.getLocationPathForLinux());
        }
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("wellcome");
    }

}