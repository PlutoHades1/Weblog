package com.zh.article.entity;

import org.springframework.data.annotation.Transient;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {
    public static final byte HOT = 1;
    public static final byte NO_HOT = 0;

    private Integer id;

    @NotNull
    @Size(max = 20)
    private String title;   //标题

    @NotNull
    @Size(max = 100)
    private String profile; //简介

    private Date publish;   //发布时间

    @NotNull
    private Integer authorId;   //作者id

    @NotNull
    private String authorName;  //作者username

    @NotNull
    private Integer category;   //文章类别

    private Byte state; //文章状态：0草稿，1未审核，2已审核，3审核未过'

    private Short favoriteCount;  //收藏数

    private Short lclickCount;  //最近点击数,每月重置,countX系数

    private Byte recommend; //是否推荐：0 不，1推荐

    @NotNull
    private String content; //内容

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile == null ? null : profile.trim();
    }

    public Date getPublish() {
        return publish;
    }

    public void setPublish(Date publish) {
        this.publish = publish;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName == null ? null : authorName.trim();
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Short getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(Short favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public Short getLclickCount() {
        return lclickCount;
    }

    public void setLclickCount(Short lclickCount) {
        this.lclickCount = lclickCount;
    }

    public Byte getRecommend() {
        return recommend;
    }

    public void setRecommend(Byte recommend) {
        this.recommend = recommend;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", profile='" + profile + '\'' +
                ", publish=" + publish +
                ", authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", category=" + category +
                ", state=" + state +
                ", favoriteCount=" + favoriteCount +
                ", lclickCount=" + lclickCount +
                ", recommend=" + recommend +
                ", content='" + content + '\'' +
                '}';
    }
}