package com.baizhi.config;

import com.baizhi.Interceptor.AdminInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private AdminInterceptor interceptorConfig;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器
        registry.addInterceptor(interceptorConfig)
                .addPathPatterns("/cmfd/jsp/*.jsp")//定义拦截路径
                .excludePathPatterns("/cmfd/jsp/login.jsp"); //排除拦截路径(也可以不排除)
    }
}
