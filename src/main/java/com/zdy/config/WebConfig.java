package com.zdy.config;

import com.zdy.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 登錄接口和註冊接口不攔截
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/user/login", "/user/register", "/pic/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pic/**") // 虛擬URL路徑
                .addResourceLocations("classpath:/files/"); // 真實本地路徑
        //.addResourceLocations("classpath:/static/files/"); // 真實本地路徑
    }

}
