package com.woniuxy.qiantai.config;

import com.woniuxy.qiantai.interceptors.JwtInterceptor;
import com.woniuxy.qiantai.interceptors.PermissionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    JwtInterceptor jwtInterceptor;

    @Autowired
    PermissionInterceptor permissionInterceptor;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //WebMvcConfigurer.super.addViewControllers(registry);

        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/register.html").setViewName("register");
        registry.addViewController("/mycart.html").setViewName("mycart");

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册interceptor
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/**");
        registry.addInterceptor(permissionInterceptor).addPathPatterns("/**/singleBook/**");
    }

}
