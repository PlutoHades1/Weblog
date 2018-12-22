package com.zh.user.service;

import com.zh.user.entity.User;

/**
 * User信息的管理
 */
public interface UserService {

    /**
     * 用户登录
     */
    User login(String username,String password);

    /**
     * 根据UserID删除User
     * @param id
     */
    void delete(Integer id);

    /**
     * 添加User
     * @param record
     */
    Integer insert(User record);

    /**
     * 更新User
     * @param record
     */
    void update(User record);

    /**
     * 更新头像
     * @param id
     * @param head
     */
    void head(Integer id,String head);

    /**
     * 更改密码
     * @param id
     * @param password
     */
    void changePassword(Integer id,String password);

    /**
     * 激活账号
     * @param id
     */
    void active(Integer id);

    /**
     * 注销账号
     * @param id
     */
    void freeze(Integer id);

    /**
     * 根据UserID查询User
     * @param id
     * @return
     */
    User findById(Integer id);

    /**
     * 根据Username查询User
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 根据手机号/邮箱 查询User
     * @param call
     * @return
     */
    User findByCall(String call);
}
