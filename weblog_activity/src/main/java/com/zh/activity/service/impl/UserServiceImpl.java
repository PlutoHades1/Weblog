package com.zh.activity.service.impl;

import com.zh.activity.service.UserService;
import com.zh.common.entity.RResult;
import org.springframework.stereotype.Service;

/**
 * 当远程调用失败时,替代的service
 */
@Service
public class UserServiceImpl implements UserService {
//    @Override
    public RResult ff(String xx) {
        System.out.println("熔断器触发了");
        return null;
    }
}
