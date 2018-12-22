package com.zh.common.entity;

import java.io.Serializable;

/**
 * 请求返回的对象
 */
public class RResult implements Serializable {

    private boolean flag;
    private Integer code;
    private String msg;
    private Object data;

    public RResult(){}

    private RResult(boolean flag){
        this.flag=flag;
    }

    public RResult(boolean flag, Integer code, String msg) {
        this.flag = flag;
        this.code = code;
        this.msg = msg;
    }

    /**
     * 成功，无数据
     * @return
     */
    public static RResult success(){
        RResult rs = new RResult(true);
        rs.code = StatusCode.OK.code;
        return rs;
    }

    /**
     * 成功，返回数据
     * @param obj 返回的数据
     * @return
     */
    public static RResult success(Object obj){
        RResult rs = new RResult(true);
        rs.data = obj;
        rs.code = StatusCode.OK.code;
        return rs;
    }

    /**
     * 成功，返回数据+消息
     * @param obj 返回的数据
     * @param msg 返回的消息
     * @return
     */
    public static RResult success(Object obj, String msg){
        RResult rs = new RResult(true);
        rs.data = obj;
        rs.msg = msg;
        rs.code = StatusCode.OK.code;
        return rs;
    }

    /**
     * 失败时，返回失败信息
     * @param sc 状态码StatusCode
     * @param msg 错误消息
     * @return
     */
    public static RResult fail(StatusCode sc, String msg){
        RResult rs = new RResult(false);
        rs.msg = msg;
        rs.code = sc.code;
        return rs;
    }


    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RResult{" +
                "flag=" + flag +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
