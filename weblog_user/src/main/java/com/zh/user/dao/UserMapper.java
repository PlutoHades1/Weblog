package com.zh.user.dao;

import com.zh.user.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);

    /**
     * 更改user的头像
     * @param id
     * @param head
     */
    @Update("update blog_vuser set head=#{head} where id=#{id}")
    void setHead(@Param("id") Integer id,@Param("head") String head);

    /**
     * 更改user状态
     * @param id
     * @param state
     */
    @Update("update blog_vuser set state=#{state} where id=#{id}")
    void setState(@Param("id") Integer id,@Param("state") byte state);

    /**
     * 更改密码
     * @param id
     * @param password
     */
    @Update("update blog_vuser set password=#{password} where id=#{id}")
    void changePassword(@Param("id")Integer id,@Param("password")String password);

    /**
     * 根据username查询user
     * @param username
     * @return
     */
    @Select("select *from blog_vuser where username=#{username}")
    User selectByUsername(@Param("username") String username);

    /**
     * 根据username查询user
     * @param call
     * @return
     */
    User selectByCall(@Param("call")String call,@Param("type") byte type);
}
