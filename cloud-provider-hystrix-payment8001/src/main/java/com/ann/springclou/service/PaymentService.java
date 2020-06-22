package com.ann.springclou.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author longquan
 * <p>
 * 节约时间，直接写成类
 */
@Service
public class PaymentService {

    /**
     * 正常访问的方法
     */
    public String paymentInfo_ok(Integer id) {
        return "线程池： " + Thread.currentThread().getName() + " paymentInfo_od,id=" + id + "\t" + "😄";
    }

    /**
     * @HystrixCommand 服务降级，返回一个友好提示
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_failHandler", commandProperties = {
            //  规定3秒之内属于正常处理时间范围
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfo_fail(Integer id) {
        // int age = 10 /0;
        int timeNumber = 3000;
        try {
            TimeUnit.MILLISECONDS.sleep(timeNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "线程池： " + Thread.currentThread().getName() + " paymentInfo_fail,id=" + id + "\t" + "😂" + "，耗时" + timeNumber / 1000 + "秒钟";
    }

    /**
     * paymentInfo_fail方法出事之后，来到这个方法处理。服务兜底
     */
    public String paymentInfo_failHandler(Integer id) {
        return "线程池： " + Thread.currentThread().getName() + " paymentInfo_failHandler，系统繁忙或运行报错，请稍后再试,id=" + id + "\t" + "😂";
    }


    /**
     * ======= 服务熔断 ========
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallBack", commandProperties = {
            //  是否开启断路器
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            //  请求次数
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            //  时间窗口期（时间范围）
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            //  失败率达到多少次之后跳闸
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("----- id 不能为负数 -----");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serialNumber;
    }

    public String paymentCircuitBreakerFallBack(@PathVariable("id") Integer id) {
        return "id 不能为负数，请稍后再试，😭  id：" + id;
    }

}
