package com.zh.article.dao;

import com.zh.article.entity.Article;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    /**
     * 根据ID查找 Article
     * @param id
     * @return
     */
    Article selectByPrimaryKey(Integer id);

    /**
     * 根据auID查找 Article
     * @param auId
     * @return
     */
    @Select("select *from blog_article where author_id=#{auId}")
    List<Article> selectByAuId(@Param("auId")Integer auId);

    //分类查询

    //

}