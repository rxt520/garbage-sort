package com.smxy.rxt.common.config;

import com.smxy.rxt.common.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/loginToIndex","/regist","/login", "/css/**", "/img/**", "/js/**", "/plugin/**", "/fonts/**","/iconfont/**","/images/**","/lib/**","/static/**","/web/**","/alipay/**");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }
}
