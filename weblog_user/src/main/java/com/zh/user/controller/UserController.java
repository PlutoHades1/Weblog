package com.zh.user.controller;

import java.util.HashMap;
import java.util.Map;
import com.zh.common.entity.RResult;
import com.zh.common.entity.StatusCode;
import com.zh.common.util.JwtUtil;
import com.zh.user.entity.User;
import com.zh.user.exception.NotDataException;
import com.zh.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 用户Controller
 */
@RequestMapping("/user")
public class UserController {
    //普通用户角色为user
    private final String ROLE = "user";
    @Autowired
    private UserService userService;

    /**
     * 用户登录,成功后跳转首页
     */
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,@RequestParam("password") String password){

        User user = userService.findByUsername(username);

        //生成Token
        String token = JwtUtil.createJWT(String.valueOf(user.getId()), user.getUsername(), ROLE);

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("roles", ROLE);

        return "index";
    }

    /**
     * 用户登出
     */

    public String logout(HttpServletResponse resp){
        //TODO 清除Token
//        resp.setHeader("");
        //跳转登录页面
        return "login";
    }

    /**
     * 更改用户头像
     */
    public RResult head(Integer id){
        //TODO 需要头像的信息
//        userService.head(id,"");
        return RResult.success();
    }

    /**
     * 用户激活账号
     */
    @GetMapping("/active/{id}")
    @ResponseBody
    public String active(@PathVariable("id") Integer id,HttpServletRequest req){
        userService.active(id);
        return "您已激活成功,thanks. 需要前往<a href='"+req.getContextPath()+"/login.html"+"'>登录</a>吗";
    }

    /**
     * 用户注销账号
     */
    public String freeze(Integer id){
        userService.freeze(id);
        //跳转登录页面
        return "login";
    }

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public String register(HttpServletRequest req, @Valid User user, Errors errors){
        //若验证未通过，返回注册表单页
        if (errors.hasErrors())
            throw new NotDataException("注册失败,再试一次吧");

        //添加用户
        Integer id = userService.insert(user);

        //当username为邮箱,则发送激活邮箱;
        //否则直接激活
        if (user.getUsername().contains("@")){
            String actUrl = req.getContextPath()+"/user/active/"+id;
            sendMail(actUrl,user.getUsername());
        }else{
            userService.active(id);
        }

        //跳转登录页面
        return "login";
    }

    /**
     * 用户更改信息
     */
    @PutMapping("/user/update/{id}")
    @ResponseBody
    public RResult update(@PathVariable("id") Integer id, @Valid User user, Errors errors){
        //若验证未通过，返回注册表单页
        if (errors.hasErrors())
            return RResult.fail(StatusCode.ERROR,"输入格式有误");

        //更新用户数据
        userService.update(user);

        return RResult.success(user);
    }

    /**
     * 检查username是否已被占用
     */
    @GetMapping("/username")
    public RResult checkPhone(@RequestParam("username") String username){
        User user = userService.findByUsername(username);
        if (user!=null)
            return RResult.fail(StatusCode.ERROR,"手机号已注册,直接去登录吧");

        return RResult.success();
    }

    @Autowired
    private JavaMailSender mailSender;
    /**
     * 简单Text邮件
     */
    private void sendMail(String actUrl,String email){
        //创建邮件
        SimpleMailMessage msg = new SimpleMailMessage();

        //邮件内容
        String text = "Hi, "+email+", Weblg欢迎你的加入,激活请点击<a href='"+actUrl+"'>"+actUrl+"</a>";
        msg.setSubject("激活你的Weblog账号");
        msg.setText(text);
        msg.setFrom("1406103364@qq.com");
        msg.setTo(email);

        //发送
        mailSender.send(msg);
    }
}
