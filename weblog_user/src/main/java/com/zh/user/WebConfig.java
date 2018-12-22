package com.zh.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//开启Rabbit注解
//@EnableRabbit
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * mvc:view-controller使用场景是在：一般springmvc都是经过Controller,
     * 但是当我们不想经过Controller，而是直接访问视图的时候,
     * 就可以通过<mvc:view-controller path="/" view-name="xx"/>。
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/login");
        registry.addViewController("/login").setViewName("/login");

        registry.addViewController("/hello").setViewName("/hello");
        registry.addViewController("/error").setViewName("/error");

    }


}
