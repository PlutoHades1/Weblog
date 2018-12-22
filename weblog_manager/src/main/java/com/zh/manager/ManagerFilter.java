package com.zh.manager;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

@Component
public class ManagerFilter extends ZuulFilter {

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
    public Object run() throws ZuulException {
        //表示不放行
//        setSendzullResponse(false);

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
