package com.zh.activity.controller;

import com.zh.common.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Controller
@CrossOrigin
public class ActivityController {
    //日志对象
    private Logger logger = LoggerFactory.getLogger(ActivityController.class);

    @Resource(name="stringRedisTemplate")
    private StringRedisTemplate strRedisTemp;    //针对字符串

    @Resource(name="redisTemplate")
    private RedisTemplate redisTemp;    //针对对象

    /**
     * 首页
     */
    @GetMapping("/")
    public String index(Model model,@CookieValue(value = "uid",defaultValue = "0") Integer id){
        //当前login user
        User user = (User)redisTemp.opsForValue().get("user_" + id);

        model.addAttribute("login",user);
        return "index";
    }

}
