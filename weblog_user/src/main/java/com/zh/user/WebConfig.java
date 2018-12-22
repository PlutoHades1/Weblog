package com.zh.user;

import com.zh.user.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
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


}
