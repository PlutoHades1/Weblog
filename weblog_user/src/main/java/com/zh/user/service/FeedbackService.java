package com.zh.user.service;

import com.zh.common.entity.PageBean;
import com.zh.common.entity.Feedback;

/**
 * Feedback信息的管理
 */
public interface FeedbackService {
    /**
     * 根据ID删除 Feedback
     * @param id
     * @return
     */
    void delete(Integer id);

    /**
     * 添加 Feedback
     * @param feedback
     * @return
     */
    void insert(Feedback feedback);

    /**
     * 设置已读
     * @param id
     */
    void read(Integer id);

    /**
     * 根据ID查询 Feedback
     * @param id
     * @return
     */
    Feedback selectById(Integer id);


    /**
     * 查询已读 Feedback
     * @return
     */
    void selectRead(PageBean<Feedback> pageBean);

    /**
     * 查询未读 Feedback
     * @return
     */
    void selectNoRead(PageBean<Feedback> pageBean);

    /**
     * 查询全部 Feedback
     * @return
     */
    void selectAll(PageBean<Feedback> pageBean);

}
