package com.zh.user.controller;

import com.zh.common.util.JwtUtil;
import io.jsonwebtoken.Claims;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;

        //获取cookie中的token,并解析
        Cookie[] cookies = req.getCookies();
        if (cookies!=null){
            for (Cookie co:cookies){
                if (co.getName().equals("token")){
                    String token = co.getValue();

                    //解析Token
                    Claims claims = JwtUtil.parseJWT(token.substring(2));
                    String id = claims.getId();
                    String subject = claims.getSubject();

                    //将数据存入Reqeust
                    Map<String,String> loginInfo = new HashMap<>();
                    loginInfo.put("id",id);
                    loginInfo.put("username",subject);
                    req.setAttribute("loginInfo", loginInfo);

                    System.out.println("数据存入Reqeust");
                }
            }
        }

        resp.addHeader("Access-Control-Allow-Origin",req.getHeader("Origin"));
        resp.addHeader("Access-Control-Allow-Methods","*");  //'*'方法
        resp.addHeader("Access-Control-Allow-Headers",req.getHeader("Access-Control-Request-Headers"));  //表示允许这个Header访问,也可以
        resp.addHeader("Access-Control-Max-Age","3600");
        resp.addHeader("Access-Control-Allow-Credentials","true");

        chain.doFilter(req,resp);
    }

    @Override
    public void destroy() {

    }
}
