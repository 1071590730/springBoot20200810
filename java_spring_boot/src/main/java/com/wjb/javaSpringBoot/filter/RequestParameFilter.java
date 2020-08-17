package com.wjb.javaSpringBoot.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

/**
 * Created by ASUS on 2020/8/17 11:29
 */
@WebFilter(filterName = "requestParameFilter",urlPatterns = "/**")
//@Order(1)设置过滤器顺序，数字越小，优先级越高
@Order(1)
public class RequestParameFilter implements Filter {
    private final static Logger LOGGER = LoggerFactory.getLogger(RequestParameFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.debug("====== Init Request Paaram Filter ====初始化==");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.debug("====== Do Request Paaram Filter ====过滤==");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        //paramsMap = httpRequest.getParameterMap();值 禁止替换
//        Map<String, String[]> paramsMap = httpRequest.getParameterMap();
//        paramsMap.put("paramKey", new String[]{"***"});

        //使用包装类 HttpServletRequestWrapper 替换
        HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(httpRequest) {
            //重写getParameter()
            @Override
            public String getParameter(String name) {
                String value = httpRequest.getParameter(name);//-----   ------取值
                if (StringUtils.isNotBlank(value)) {//------------------   ---判断 value 不为空
                    return value.replaceAll("fuck", "***");//返回替换值
                }
                return super.getParameter(name);//----------------------------为空 调用父类方法
            }

            //替换以注解（@RequestParam(value = "paramKey") String paramValue）得到的参数
            @Override
            public String[] getParameterValues(String name) {
                String[] values = httpRequest.getParameterValues(name);//取值
                if (values != null && values.length > 0) {//判断
                    for (int i = 0; i < values.length; i ++) {//遍历
                        values[i] = values[i].replaceAll("fuck", "***");//替换
                    }
                    return values;
                }
                return super.getParameterValues(name);
            }
        };
        //request返回替换后的包装类wrapper
        chain.doFilter(wrapper, response);
    }

    @Override
    public void destroy() {
        LOGGER.debug("====== Destroy Request Paaram Filter ====销毁（结束）==");
    }
}
