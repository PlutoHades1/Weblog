package com.zh.user.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zh.common.entity.PageBean;
import com.zh.user.dao.FeedbackMapper;
import com.zh.user.entity.Feedback;
import com.zh.user.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public void delete(Integer id) {
        feedbackMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(Feedback feedback) {
        feedback.setIsRead(Feedback.NO_READ);
        feedback.setCreateTime(new Date());
        feedbackMapper.insert(feedback);
    }

    @Override
    public void read(Integer id) {
        feedbackMapper.setRead(id,Feedback.READED);
    }

    @Override
    public Feedback selectById(Integer id) {
        Feedback feedback = feedbackMapper.selectByPrimaryKey(id);
        return feedback;
    }

    @Override
    public void selectRead(PageBean<Feedback> pageBean) {
        Page<Object> page = PageHelper.startPage(pageBean.getPageNum(), pageBean.getSize());

        //查询
        List<Feedback> list = feedbackMapper.selectRead(Feedback.READED);

        pageBean.setPageTotal(page.getPages()); //总页数
        pageBean.setTotal(page.getTotal()); //总记录数
        pageBean.setRows(list); //记录数据
    }

    @Override
    public void selectNoRead(PageBean<Feedback> pageBean) {
        Page<Object> page = PageHelper.startPage(pageBean.getPageNum(), pageBean.getSize());

        //查询
        List<Feedback> list = feedbackMapper.selectRead(Feedback.NO_READ);

        pageBean.setPageTotal(page.getPages()); //总页数
        pageBean.setTotal(page.getTotal()); //总记录数
        pageBean.setRows(list); //记录数据
    }

    @Override
    public void selectAll(PageBean<Feedback> pageBean) {
        Page<Object> page = PageHelper.startPage(pageBean.getPageNum(), pageBean.getSize());

        //查询
        List<Feedback> list = feedbackMapper.selectAll();

        pageBean.setPageTotal(page.getPages()); //总页数
        pageBean.setTotal(page.getTotal()); //总记录数
        pageBean.setRows(list); //记录数据
    }
}
