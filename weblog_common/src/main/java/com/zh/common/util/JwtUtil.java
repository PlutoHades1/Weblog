package com.zh.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Administrator on 2018/4/11.
 */
public class JwtUtil {
    //盐
    private final static String salt = "micat";
    //30分钟
    private final static long ttl = 1800000L;

    private JwtUtil(){}


    /**
     * 生成JWT
     * @param id
     * @param subject
     * @return
     */
    public static String createJWT(String id, String subject, String roles) {
        Date now = new Date();

        JwtBuilder builder = Jwts.builder()
                .setId(id)   //用户ID
                .setSubject(subject)    //用户名
                .setIssuedAt(now)   //登录时间
                .signWith(SignatureAlgorithm.HS256, salt)    //头信息(编码,盐)
                .claim("roles", roles); //自定义的属性
        if (ttl > 0) {
            builder.setExpiration( new Date( now.getTime() + ttl)); //过期时间
        }
        return builder.compact();   //返回JWT字串
    }

    /**
     * 解析JWT
     * @param jwtStr
     * @return
     */
    public static Claims parseJWT(String jwtStr){
        Claims claims = Jwts.parser()
                .setSigningKey(salt)
                .parseClaimsJws(jwtStr)
                .getBody();
        return claims;
    }



}
