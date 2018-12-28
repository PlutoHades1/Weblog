package com.zh.activity.controller;


import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ActivityFilter implements Filter {
    @Resource(name="redisTemplate")
    private RedisTemplate redisTemp;    //针对对象

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;

        resp.addHeader("Access-Control-Allow-Origin",req.getHeader("Origin"));
        resp.addHeader("Access-Control-Allow-Methods","*");  //'*'方法
        resp.addHeader("Access-Control-Allow-Headers",req.getHeader("Access-Control-Request-Headers"));  //表示允许这个Header访问,也可以
        resp.addHeader("Access-Control-Max-Age","3600");
        resp.addHeader("Access-Control-Allow-Credentials","true");

        Cookie[] cookies = req.getCookies();
        for(Cookie co:cookies){
            if (co.getName().equals("uid")){
                redisTemp.expire("user_"+co.getValue(),30, TimeUnit.MINUTES);
            }
        }



        chain.doFilter(req,resp);
    }

    @Override
    public void destroy() {

    }

}
