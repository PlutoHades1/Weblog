package com.zh.user.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.zh.common.entity.RResult;
import com.zh.common.entity.StatusCode;
import com.zh.common.entity.Feedback;
import com.zh.common.entity.User;
import com.zh.user.exception.NotDataException;
import com.zh.user.service.FeedbackService;
import com.zh.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 用户Controller
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    //日志对象
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private FeedbackService feedbackService;

    @Resource(name="stringRedisTemplate")
    private StringRedisTemplate strRedisTemp;    //针对字符串

    @Resource(name="redisTemplate")
    private RedisTemplate redisTemp;    //针对对象

    /**
     * 用户登录,成功后跳转首页
     */
    @PostMapping("/login")
    public RResult login(HttpServletResponse resp, @RequestParam("username") String username,@RequestParam("password") String password){
        User user = userService.login(username,password);
        logger.info("--用户登录了-- : "+user);

        //登录信息放入Redis
        redisTemp.opsForValue().set("user_"+user.getId(),user,30, TimeUnit.MINUTES);

        Cookie cookie = new Cookie("uid",user.getId()+"");
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        cookie.setDomain("localhost");
        resp.addCookie(cookie);

        return RResult.success(user);
    }

    /**
     * 用户登出
     */
    @GetMapping("/logout")
    public RResult logout(@CookieValue("uid") String id, HttpServletResponse resp){
        //清除cookie
        Cookie cookie = new Cookie("uid","");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setDomain("localhost");
        resp.addCookie(cookie);

        //清除redis
        redisTemp.delete("user_"+id);
        return RResult.success();
    }

    /**
     * 更改用户头像
     * 头像保存到文件服务器
     */
    @PutMapping("/head/{id}")
    public RResult head(@PathVariable("id") Integer id){
        //TODO 需要头像的信息
//        userService.head(id,"");
        return RResult.success();
    }

    /**
     * 用户激活账号
     */
    @GetMapping("/active/{id}")
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
    public RResult freeze(@PathVariable("id") Integer id,@Valid Feedback feedback, Errors errors){
        if (errors.hasErrors())
            throw new NotDataException("提交失败,再试一次吧");

        //意见反馈 入库
        feedback.setUserId(id);
        feedbackService.insert(feedback);

        //注销
        userService.freeze(id);

        //跳转到首页
        Map<String,String> map = new HashMap<>(1);
        map.put("toUrl","/index.html");
        return RResult.success(map);
    }

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public RResult register(String code,String call,HttpServletRequest req, @Valid User user, Errors errors){

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

        //当username为邮箱,则发送激活邮箱
        //TODO 需要调用消息队列
        if (user.getUsername().contains("@")){
            String actUrl = "http://localhost:"+req.getLocalPort()+req.getContextPath()+"/user/active/"+id+"_"+(new Date().getTime()+7200000);
        }

        if(!isSend)
            userService.active(id);

        //跳转到首页
        Map<String,String> map = new HashMap<>(1);
        map.put("toUrl","/index.html");
        return RResult.success(map);
    }

    /**
     * 用户更改账号
     */
    @PutMapping("/update/{id}")
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
    public RResult checkname(@RequestParam("username") String username){
        User user = userService.findByUsername(username);
        if (user!=null)
            return RResult.fail(StatusCode.ERROR,"此用户名已存在,换一个吧");

        return RResult.success();
    }

    /**
     * 检查手机号/邮箱 是否已被占用
     */
    @GetMapping("/checkcall")
    public RResult checkcall(@RequestParam("call") String call){
        User user = userService.findByCall(call);
        if (user!=null)
            return RResult.fail(StatusCode.ERROR,"手机号或邮箱已注册,直接去登录吧");

        return RResult.success();
    }

    /**
     * 根据Id查询User
     */
    @GetMapping("/find/{id}")
    public RResult findById(@PathVariable(value = "id") Integer id){
        User user = userService.findById(id);
        return RResult.success(user);
    }

}
