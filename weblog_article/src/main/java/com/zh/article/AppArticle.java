package com.zh.article;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 主程序类
 */
@SpringBootApplication
//注册为Eureka的客户端,(需要在yml中配置)
@EnableEurekaClient
//开启发现服务
@EnableDiscoveryClient
//使用Feign去发现服务
@EnableFeignClients
//扫描Mybatis的mapper
@MapperScan("com.zh.article.dao")
public class AppArticle {
    public static void main(String[] args) {
        //启动应用
        SpringApplication.run(AppArticle.class);
    }

}