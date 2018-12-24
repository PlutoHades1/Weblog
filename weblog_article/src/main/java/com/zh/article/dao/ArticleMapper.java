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
     * 默认最新排序
     * @param auId
     * @return
     */
    @Select("select *from blog_article where author_id=#{auId} order by #{orderCd} #{way}")
    List<Article> selectByAuId(@Param("auId")Integer auId,@Param("orderCd")String orderCd,@Param("way") String way);

    /**
     * 分类查询,
     * 默认最热,即最近多人访问
     * @param cId
     * @param orderCd
     * @return
     */
    @Select("select *from blog_article where category=#{cId} order by #{orderCd}")
    List<Article> selectByCate(@Param("cId")Integer cId,@Param("orderCd") String orderCd);



    //

}