package com.zh.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 没有数据异常
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Not found data")
public class NotDataException extends RuntimeException{

    public NotDataException() {}

    public NotDataException(String message) {
        // 把参数传递给Throwable的带String参数的构造方法
        super(message);
    }

}