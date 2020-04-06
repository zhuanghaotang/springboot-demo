package com.novellatonyatt.config;

import com.novellatonyatt.interceptor.AccessInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: Zhuang HaoTang
 * @Date: 2020/4/5 17:09
 * @Description:
 */
@Configuration
public class InterceptorRegistration implements WebMvcConfigurer {

    @Bean
    public AccessInterceptor accessInterceptorBean() {
        return new AccessInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessInterceptorBean());
    }
}
