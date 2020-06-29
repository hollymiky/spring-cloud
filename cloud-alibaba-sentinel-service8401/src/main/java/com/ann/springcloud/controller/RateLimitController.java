package com.ann.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.ann.springcloud.entities.CommonResult;
import com.ann.springcloud.entities.Payment;
import com.ann.springcloud.myhandler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author longquan
 */
@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult byResource(){
        return new CommonResult(200,"按资源名限流测试....",new Payment(2020L,"serial001"));
    }

    public CommonResult handleException(BlockException e){
        return new CommonResult(444,e.getClass().getCanonicalName()+"\t"+" 服务不可用");
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl(){
        return new CommonResult(200,"按url限流测试....",new Payment(2020L,"serial002"));
    }

    //  CustomerBlockHandler
    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",blockHandlerClass = CustomerBlockHandler.class,blockHandler = "handlerException2")
    public CommonResult customerBlockHandler(){
        return new CommonResult(200,"自定义....",new Payment(2020L,"serial003"));
    }
}
