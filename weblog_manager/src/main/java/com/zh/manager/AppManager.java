package com.zh.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *
 */
@SpringBootApplication
//注册为Eureka的客户端,(需要在yml中配置)
@EnableEurekaClient
//开启发现服务
@EnableDiscoveryClient
//使用Feign去发现服务
@EnableFeignClients
//使用网关
@EnableZuulProxy
public class AppManager {
    public static void main( String[] args ){
        SpringApplication.run(AppManager.class);
    }
}
