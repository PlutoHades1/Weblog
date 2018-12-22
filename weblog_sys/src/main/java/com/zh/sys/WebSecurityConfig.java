package com.zh.sys;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring Security的配置类
 *      用户后台管理账号的权限控制
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 授权
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //授权规则
        http.authorizeRequests()
                .antMatchers("/**").permitAll()   //permitAll()表示不需要验证
//                .antMatchers("/**").hasRole("user")
//                .antMatchers("/bb/**").hasRole("bb")
                .and().csrf().disable();

        //登录
        http.formLogin()
                .usernameParameter("username")  //表单参数
                .passwordParameter("password")  //表单参数
                .loginPage("/browse/login")  //登录页面
                .loginProcessingUrl("/user/login"); //登录请求的URL

        //注销
        http.logout()
                .logoutSuccessUrl("/login.html");    //注销后跳转的URL
        //记住我
        http.rememberMe()
                .rememberMeParameter("rememberMe"); ////表单参数

    }


    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        //        auth.jdbcAuthentication()
        auth.inMemoryAuthentication()
                .withUser("xiaoming").password("1234").roles("root","system")
                .and().withUser("xiaowang").password("1234").roles("system");

    }

}