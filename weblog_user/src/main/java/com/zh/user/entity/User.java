package com.zh.user.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private Integer id; //用户ID

    @NotNull
    @Size(min = 4,max = 10)
    private String nickname;    //昵称

    @NotNull
    @Pattern(regexp = "(^[\\w.\\-]+@(?:[a-z0-9]+(?:-[a-z0-9]+)*\\.)+[a-z]{2,3}$)|(^1[3|4|5|8]\\d{9}$)")
    private String username;    //账号

    private String phone;    //手机号

    private String email;   //邮箱

    @NotNull
    @Size(min = 4,max = 10)
    private String password;    //密码

    @Size(max = 50)
    private String oneword; //个性签名

    private String url;     //个人博客首页

    private Byte state;     //账号状态：0未激活，1正常，2注销

    private Date createTime;    //账号创建时间及更改时间

    private String head;    //头像

    public static final String DEFAULT_HEAD="group1/M00/00/01/wKgrPVwbSHWAfK-7AACxoUZ_qkM71.jpeg";
    public static final byte STATE_NOACTIVE = 0;
    public static final byte STATE_ACTIVE = 1;
    public static final byte STATE_INVALID = 2;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getOneword() {
        return oneword;
    }

    public void setOneword(String oneword) {
        this.oneword = oneword == null ? null : oneword.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head == null ? null : head.trim();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", oneword='" + oneword + '\'' +
                ", url='" + url + '\'' +
                ", state=" + state +
                ", createTime=" + createTime +
                ", head='" + head + '\'' +
                '}';
    }
}