package com.oceancoding.ws.ocean.config;

import com.oceancoding.ws.ocean.interceptor.ExternalServiceInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ExternalServiceInterceptor()).addPathPatterns("/api/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
