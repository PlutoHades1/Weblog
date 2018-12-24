package com.zh.article.service.impl;

import com.zh.article.dao.ArticleMapper;
import com.zh.article.entity.Article;
import com.zh.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    @Transactional
    public void delete(Integer id) {
        //TODO
        //删除文章,标签的关联记录

        //删除相关的评论

        //从用户喜爱的文章中移除

        //删除文章
        articleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(Article article) {
        article.setLclickCount((short)0);

        article.setFavoriteCount((short)0);
        article.setPublish(new Date());
        article.setRecommend(Article.NO_HOT);

        articleMapper.insert(article);
    }

    @Override
    public void update(Article article) {
        article.setPublish(new Date());

        articleMapper.updateByPrimaryKeySelective(article);
    }
}
