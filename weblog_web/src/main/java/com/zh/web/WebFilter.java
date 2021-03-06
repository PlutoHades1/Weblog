package com.zh.web;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.zh.common.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
public class WebFilter extends ZuulFilter {

    /**
     * 过滤器类型: "pre",过滤前执行; "post",过滤后执行
     */
    @Override
    public String filterType(){
        return "pre";
    }

    /**
     * 是否开启过滤器
     */
    @Override
    public boolean shouldFilter(){
        return true;
    }

    @Override
    public Object run() throws ZuulException{
        //使用Zuul的工具获取HttpRequest
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest req = context.getRequest();

        /*String token = req.getHeader("token");
        if (token!=null){
            if (token.startsWith("m_")){
                context.addZuulRequestHeader("token",token);
            }else{

            }
        }*/

        Cookie[] cookies = req.getCookies();
        if (cookies!=null){
            for (Cookie co:cookies){
                if (co.getName().equals("token")){
                    String token = co.getValue();
                    if (!token.startsWith("m_")){
                        context.setSendZuulResponse(false);
                        break;
                    }
                }
            }
        }

        //表示网Request的Header中添加数据
//        context.addZuulRequestHeader("","");

        //表示不放行
//        context.setSendZuulResponse(false);

        return null;
    }

    /**
     * 过滤器顺序,越小越先
     */
    @Override
    public int filterOrder(){
        return 5;
    }

}
