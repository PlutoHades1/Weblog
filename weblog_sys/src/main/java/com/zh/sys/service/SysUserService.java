package com.zh.sys.service;

import com.zh.sys.entity.SysUser;

public interface SysUserService {


    void delete(Integer id);

    void insert(SysUser user);

    void update(SysUser user);

    /**
     * 根据ID查询SysUser
     * @param id
     * @return
     */
    SysUser findById(Integer id);

    /**
     * 根据username查询SysUser
     * @param username
     * @return
     */
    SysUser findByUsername(String username);


}
