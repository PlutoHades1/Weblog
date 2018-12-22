package com.zh.common.entity;

import java.io.Serializable;

/**
 * 请求返回的状态
 */
public enum StatusCode implements Serializable {
    OK(2000),   //成功
    ERROR(3000),    //失败
    LOGINERROR(3010),   //用户名or密码错误
    ACCESSERROR(3020),  //权限不足
    REPERROR(3030),    //重复操作
    REMOTEERROR(4000);  //远程调用失败

    final int code;
    StatusCode(int code){
        this.code = code;
    }
}
