package com.ann.springcloud.controller;

import com.ann.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author longquan
 */
@RestController
@Slf4j
@RequestMapping("/consumer")
//  全局fallback
@DefaultProperties(defaultFallback = "paymentGlobalFallbackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id) {
        log.info("-----consumer---- /hystrix/ok");
        return paymentHystrixService.paymentInfo_ok(id);
    }

    @GetMapping("/hystrix/timeout/{id}")
    //  全局fallback只需要这个注解就行
    @HystrixCommand
   /* @HystrixCommand(fallbackMethod = "paymentInfo_failHandler", commandProperties = {
            //  客户端最多只能等待1.5秒，超过的话就执行paymentInfo_failHandler方法
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })*/
    public String paymentInfo_timeout(@PathVariable("id") Integer id) {
        log.info("-----consumer---- /hystrix/timeout");
        // int i = 10/0;
        return paymentHystrixService.paymentInfo_timeout(id);
    }

    /**
     * paymentInfo_timeout方法出事之后，来到这个方法处理。服务兜底
     */
    public String paymentInfo_failHandler(Integer id){
        return "我是消费者80，对付支付系统繁忙请10秒后再试或者自己运行出错请检查自己😢";
    }

    /**
     * 全局FallBack
     */
    public String paymentGlobalFallbackMethod(){
        return "Global异常处理信息，请稍后再试....";
    }


}
