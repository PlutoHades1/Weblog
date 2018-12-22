package com.zh.user.service;

import com.zh.common.entity.RResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 调用eureka中的其他服务
 *      还需要在App中添加 @EnableDiscoveryClient,@EnableFeignClients
 */
//调用服务,value是"服务名",fallback是熔断时调用的类
//  当使用熔断器时,需要配置yml
//@FeignClient(value="",fallback="RPCServiceImpl")
public interface RPCService {

//    @ReqeustMapping(value="",method= RequestMethod.GET)
//    RResult ff(@PathVariable("xx") String xx);
}
