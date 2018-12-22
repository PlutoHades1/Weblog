package com.zh.user.dao;

import com.zh.user.entity.Feedback;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface FeedbackMapper {
    /**
     * 根据ID删除 Feedback
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 添加 Feedback
     * @param record
     * @return
     */
    int insert(Feedback record);

    int insertSelective(Feedback record);

    int updateByPrimaryKeySelective(Feedback record);

    int updateByPrimaryKey(Feedback record);

    /**
     * 设置isRead状态
     * @param id
     * @param state
     */
    @Update("update blog_feedback set is_read=#{state} where id=#{id}")
    void setRead(@Param("id") Integer id,@Param("state") Integer state);

    /**
     * 根据ID查询 Feedback
     * @param id
     * @return
     */
    @Select("select *from blog_feedback where id=#{id}")
    Feedback selectByPrimaryKey(@Param("id") Integer id);


    /**
     * 根据is_read 查询全部 Feedback
     * @return
     */
    @Select("select *from blog_feedback where is_read=#{state}")
    List<Feedback> selectRead(@Param("state") Integer state);

    /**
     * 查询全部 Feedback
     * @return
     */
    @Select("select *from blog_feedback")
    List<Feedback> selectAll();

}