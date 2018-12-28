package com.zh.common.entity;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class Feedback implements Serializable {
    public static final byte NO_READ=0;
    public static final byte READED=1;

    private Integer id;

    private Integer userId; //用户ID

    @Size(max = 250)
    private String problem; //遇到的问题

    @Size(max = 250)
    private String advice;  //网友的建议

    private Byte isRead;    //反馈内容是否已读,0-未读,1-已读

    private String email;   //网友的联系方式(email)

    private Date createTime;    //创建时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem == null ? null : problem.trim();
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice == null ? null : advice.trim();
    }

    public Byte getIsRead() {
        return isRead;
    }

    public void setIsRead(Byte isRead) {
        this.isRead = isRead;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}