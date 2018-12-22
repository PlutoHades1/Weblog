package com.zh.user.service.impl;

import com.zh.user.dao.UserMapper;
import com.zh.user.entity.User;
import com.zh.user.exception.NotDataException;
import com.zh.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public User login(String username, String password) {
        //数据库中查询
        User user = userMapper.selectByUsername(username);

        if (user==null || !encoder.matches(password, user.getPassword()))
            throw new NotDataException("账号或密码错误");

        if (user.getState()== User.STATE_NOACTIVE)
            throw new NotDataException("账号未激活");

        return user;
    }

    @Override
    public void delete(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(User user) {
//        user.setId();
//        user.setNickname();
//        user.setUsername();
//        user.setPassword();
//        user.setEmail();
//        user.setProfile();
//        user.setHead();

        //手机号或邮箱
        String username = user.getUsername();
        if(username.contains("@"))
            user.setPassword(username);
        else
            user.setPhone(username);
        //默认头像
        user.setUrl(User.DEFAULT_HEAD);
        //默认状态
        user.setState(User.STATE_NOACTIVE);
        user.setCreateTime(new Date());

        userMapper.insertSelective(user);

        return user.getId();
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void head(Integer id,String head) {
        userMapper.setHead(id,head);
    }

    @Override
    public void changePassword(Integer id, String password) {
        userMapper.changePassword(id,password);
    }

    @Override
    public void active(Integer id) {
        userMapper.setState(id,User.STATE_ACTIVE);
    }

    @Override
    public void freeze(Integer id) {
        userMapper.setState(id,User.STATE_INVALID);
    }

    @Override
    public User findById(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        User user = userMapper.selectByUsername(username);
        return user;
    }
}
