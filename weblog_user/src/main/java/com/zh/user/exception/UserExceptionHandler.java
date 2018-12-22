package com.zh.user.exception;

import com.zh.common.entity.RResult;
import com.zh.common.entity.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * 全局异常处理方式一：
 *  自定义一个异常控制器类
 *      使用@ControllerAdvice来自定义,(已经包含了@Component)
 * @Autohr zh
 */
@ControllerAdvice
public class UserExceptionHandler {

    /**
     * 处理自定义Exception异常
     *      注解@ExceptionHandler的使用：表示当出现xxException时，调用ff方法
     *      返回类型String or ModelAndView or json字串，(方法参数不能有Model、Map)
     */
    //响应错误页面，MyException的状态码已设为404，也可以在request中设置
    /*@ExceptionHandler()
    public ModelAndView exception(Exception e){

        ModelAndView mv = new ModelAndView();
        mv.setStatus(HttpStatus.NOT_FOUND);
        mv.setViewName("error");
        mv.addObject("message",e.getMessage());

        return mv;
    }*/

    /**
     * 响应错误json字串
     */
    @ExceptionHandler(NotDataException.class)
    @ResponseBody
    public RResult exception2(Exception e, HttpServletResponse resp){
        Map<String,Object> map = new HashMap<>();
        map.put("message",e.getMessage());
        return RResult.fail(StatusCode.ERROR,e.getMessage());
    }
}
