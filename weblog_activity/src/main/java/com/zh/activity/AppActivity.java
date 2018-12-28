package com.zh.activity;

import com.zh.activity.controller.ActivityFilter;
import com.zh.common.entity.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
//注册为Eureka的客户端,(需要在yml中配置)
@EnableEurekaClient
//开启发现服务
@EnableDiscoveryClient
//使用Feign去发现服务
@EnableFeignClients
//扫描Mybatis的mapper
@MapperScan("com.zh.activity.dao")
public class AppActivity {
    public static void main(String[] args) {
        SpringApplication.run(AppActivity.class);
    }

    /**
     * (默认使用Jdk的序列化策略)
     * 自定义为Json序列化
     */
    @Bean
    public RedisTemplate<Object, User> diyredisTemplate(RedisConnectionFactory redisCF){
        RedisTemplate<Object, User> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisCF);

        Jackson2JsonRedisSerializer<User> json = new Jackson2JsonRedisSerializer<>(User.class);

        redisTemplate.setDefaultSerializer(json);
        return redisTemplate;
    }

    /**
     * 注册Filter
     */
    @Bean
    public FilterRegistrationBean userFilter(){
        FilterRegistrationBean fr = new FilterRegistrationBean<>();
        fr.setFilter(new ActivityFilter());
        fr.setUrlPatterns(Arrays.asList("/*"));
        //一些初始化参数
        Map<String,String> map = new HashMap<>();
        map.put("exclusions","*.js,*.css,/druid/*");
        fr.setInitParameters(map);
        return fr;
    }
}
