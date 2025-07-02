package com.fort4.cnc.common.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fort4.cnc.common.custom.LoginUserArgumentResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginUserArgumentResolver loginUserArgumentResolver;
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 정적 이미지 파일 매핑
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:///C:/upload/");
    }
    
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginUserArgumentResolver);
    }
}

