package com.zh.user.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.zh.common.entity.RResult;
import com.zh.common.entity.StatusCode;
import com.zh.common.util.JwtUtil;
import com.zh.user.entity.Feedback;
import com.zh.user.entity.User;
import com.zh.user.exception.NotDataException;
import com.zh.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 用户Controller
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    //普通用户角色为user
    private final String ROLE = "user";

    @Autowired
    private UserService userService;

    @Resource(name="stringRedisTemplate")
    StringRedisTemplate strRedisTemp;    //针对字符串

    @Resource(name="redisTemplate")
    RedisTemplate redisTemp;    //针对对象

    /**
     * 用户登录,成功后跳转首页
     */
    @PostMapping("/login")
    public RResult login(@RequestParam("username") String username,@RequestParam("password") String password){
        User user = userService.login(username,password);

        //保存登录用户User对象
        redisTemp.opsForValue().set("user_"+user.getId(),user,30,TimeUnit.MINUTES);

        Map<String,String> map = new HashMap<>(1);
        map.put("toUrl","index.html");
        return RResult.success(map);
    }

    /**
     * 用户登出
     */
    @GetMapping("/logout/{id}")
    public RResult logout(@PathVariable("id") Integer id, HttpServletResponse resp){

        redisTemp.delete("user_" + id);

        //跳转登录页面
        Map<String,String> map = new HashMap<>(1);
        map.put("toUrl","login.html");
        return RResult.success(map);
    }

    /**
     * 更改用户头像
     * 头像保存到文件服务器
     */
    @ResponseBody
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
    public String active(@PathVariable("id") String id,HttpServletRequest req){
        String[] s = id.split("_");
        if (s.length>1){
            boolean exp = Long.parseLong(s[1])<System.currentTimeMillis();
            if (exp)
                return "抱歉,链接已失效";
        }

        userService.active(Integer.valueOf(s[0]));
        return "您已激活成功,thanks. 需要前往<a href='"+req.getContextPath()+"/login.html'>登录</a>吗";
    }

    /**
     * 用户注销账号
     *      需要填写注销原因,并申请注销,
     */
    @PostMapping("/freeze/{id}")
    public RResult freeze(@PathVariable("id") Integer id, Feedback feedback){
        //原因即及建议入库
        feedback.setUserId(id);
        feedbackService.

        //注销
        userService.freeze(id);

        //跳转到首页
        Map<String,String> map = new HashMap<>(1);
        map.put("toUrl","index.html");
        return RResult.success(map);
    }

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public String register(String code,String call,HttpServletRequest req, @Valid User user, Errors errors){
        //校验数据
        if (StringUtils.isEmpty(code) || errors.hasErrors())
            throw new NotDataException("注册失败,再试一次吧");
        if (call.contains("@"))
            user.setEmail(call);
        else
            user.setPhone(call);

        //添加用户
        Integer id = userService.insert(user);

        //是否已经发送邮件了,若没有发送,则直接激活
        boolean isSend = false;

        //当username为邮箱,则发送激活邮箱;
        if (user.getUsername().contains("@")){
            String actUrl = "http://localhost:"+req.getLocalPort()+req.getContextPath()+"/user/active/"+id+"_"+(new Date().getTime()+7200000);

            for(int i=0;i<2;i++){
                try {
                    sendMail(actUrl,user.getUsername());
                    isSend = true;
                    break;
                } catch (MessagingException e) {
                    logger.error("激活邮件发送失败.. [cause]:"+e.getCause());
                }
            }
        }

        if(!isSend)
            userService.active(id);

        //跳转注册成功页面
        return "redirect:/login.html";
    }

    /**
     * 用户更改信息
     */
    @PutMapping("/update/{id}")
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
    @GetMapping("/checkname")
    @ResponseBody
    public RResult checkname(@RequestParam("username") String username){
        User user = userService.findByUsername(username);
        if (user!=null)
            return RResult.fail(StatusCode.ERROR,"此用户名已存在,换一个吧");

        return RResult.success();
    }

    /**
     * 检查 手机号/邮箱 是否已被占用
     */
    @GetMapping("/checkcall")
    @ResponseBody
    public RResult checkcall(@RequestParam("call") String call){
        User user = userService.findByCall(call);
        if (user!=null)
            return RResult.fail(StatusCode.ERROR,"手机号或邮箱已注册,直接去登录吧");

        return RResult.success();
    }



    @Autowired
    private JavaMailSender mailSender;
    /**
     * HTML邮件
     */
    private void sendMail(String actUrl,String email) throws MessagingException {
        //创建邮件
        MimeMessage msg = mailSender.createMimeMessage();

        //邮件内容,需要MimeMessageHelper类操作
        MimeMessageHelper helper = new MimeMessageHelper(msg,true);

        String text = "Hi, "+email+", Weblg欢迎你的加入,请在两小时内激活,激活请点击<a href='"+actUrl+"'>"+actUrl+"</a>";
        helper.setSubject("激活你的Weblog账号");
        helper.setText(text,true);
        helper.setFrom("1406103364@qq.com");
        helper.setTo(email);
        //携带附件
//          helper.addAttachment("文件名",new File("文件路径"));

        //发送
        mailSender.send(msg);
    }
}
