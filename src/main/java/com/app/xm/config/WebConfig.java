package com.app.xm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.app.xm.common.JwtInterceptor;

import jakarta.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Resource
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor).excludePathPatterns("/login","/register");
    }

}