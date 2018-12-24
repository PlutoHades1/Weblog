package com.zh.article.exception;

import com.zh.common.entity.RResult;
import com.zh.common.entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
public class ArticleExceptionHandler {

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
