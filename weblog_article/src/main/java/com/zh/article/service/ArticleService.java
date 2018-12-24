package com.zh.article.service;

import com.zh.article.entity.Article;

public interface ArticleService {

    /**
     * 根据ID删除
     * 删除文章时:
     *      会删除文章,标签的关联记录
     *      会删除相关的评论
     *      会从用户喜爱的文章中移除
     * @param id
     * @return
     */
    void delete(Integer id);

    /**
     * 添加
     * @param article
     * @return
     */
    void insert(Article article);

    /**
     * 更新
     * @param article
     * @return
     */
    void update(Article article);

}
