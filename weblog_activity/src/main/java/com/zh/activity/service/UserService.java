package com.zh.activity.service;

import com.zh.activity.service.impl.UserServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 调用eureka中的其他服务
 *      还需要在App中添加 @EnableDiscoveryClient,@EnableFeignClients
 */
//调用服务,value是"服务名",fallback是熔断时调用的类
//  当使用熔断器时,需要配置yml
@FeignClient(value="weblog-user",fallback= UserServiceImpl.class)
public interface UserService {


}