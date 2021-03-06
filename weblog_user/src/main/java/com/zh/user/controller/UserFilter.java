package com.zh.user.controller;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserFilter implements Filter {
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

        chain.doFilter(req,resp);
    }

    @Override
    public void destroy() {

    }
}
