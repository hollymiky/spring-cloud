package com.ann.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.ann.springcloud.entities.CommonResult;
import com.ann.springcloud.entities.Payment;
import com.ann.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author longquan
 */
@RestController
@Slf4j
@RequestMapping("/consumer")
public class CircleBreakerController {

    @Value("${service-url.nacos-user-service}")
    private String serverUrl;

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/fallback/{id}")
    // @SentinelResource(value = "fallback")   //  没有配置
    // @SentinelResource(value = "fallback", fallback = "handlerFallback")       // fallback只负责业务异常
    // @SentinelResource(value = "fallback",blockHandler = "blockHandler")      // blockHandler只负责sentinel控制台配置规则
    // 两者都配置时，先blockHandler 后fallback
    // exceptionsToIgnore 如果出现此IllegalArgumentException异常，兜底fallback方法不降级
    @SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "blockHandler",
                        exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<Payment> fallback(@PathVariable("id") Long id) {
        CommonResult<Payment> result = restTemplate.getForObject(serverUrl + "/paymentSQL/" + id, CommonResult.class, id);
        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException，非法参数异常...");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException，该id没有对应的记录，空指针异常...");
        }
        return result;
    }

    //  fallback 服务降级
    public CommonResult<Payment> handlerFallback(Long id, Throwable e) {
        Payment payment = new Payment(id, null);
        return new CommonResult<>(444, "业务代码出现异常，此兜底方法handlerFallback进行处理，exception内容： " + e.getMessage(), payment);
    }


    //  blockHandler
    public CommonResult<Payment> blockHandler(Long id, BlockException e){
        Payment payment = new Payment(id, null);
        return new CommonResult<>(445, "blockHandler-sentinel限流，BlockException内容： " + e.getMessage(), payment);
    }




    // ============== OpenFeign ============
    @Resource
    private PaymentService paymentService;

    @GetMapping("/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        return paymentService.paymentSQL(id);
    }

}
