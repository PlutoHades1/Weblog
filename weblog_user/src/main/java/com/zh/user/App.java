package com.zh.user;

import com.zh.user.entity.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.concurrent.CountDownLatch;

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
@MapperScan("com.zh.user.dao")
public class App{
    public static void main(String[] args) {
        //启动应用
        SpringApplication.run(App.class);
    }



    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
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


    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("chat"));

        return container;
    }




    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

    /**
     * 自定义修改缓存管理器
     */
    /*public RedisCacheManager diyredisCacheManager(RedisTemplate<Object,Object> redisTemplate){
        //TODO 有问题
        RedisCacheManager cacheManager = new RedisCacheManager();
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }*/
}
