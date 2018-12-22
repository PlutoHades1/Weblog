package com.zh.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *
 */
@SpringBootApplication
//注册到Eureka中
@EnableEurekaClient
public class App {
    public static void main( String[] args ){
        SpringApplication.run(App.class);
    }
}
