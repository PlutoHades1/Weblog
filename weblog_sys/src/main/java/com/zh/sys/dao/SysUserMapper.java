package com.zh.sys.dao;

import com.zh.sys.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**
     * 根据ID查询SysUser
     * @param id
     * @return
     */
    SysUser selectByPrimaryKey(Integer id);

    /**
     * 根据username查询SysUser
     * @param username
     * @return
     */
    @Select("select *from sys_user where username=#{username}")
    SysUser selectByUsername(@Param("username") String username);


}