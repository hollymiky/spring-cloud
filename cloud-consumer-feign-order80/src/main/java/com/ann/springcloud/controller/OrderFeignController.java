package com.ann.springcloud.controller;

import com.ann.springcloud.entities.CommonResult;
import com.ann.springcloud.entities.Payment;
import com.ann.springcloud.serivce.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author longquan
 */
@Slf4j
@RestController
@RequestMapping("/consumer")
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout(){
        log.info("进入client paymentFeignTimeout()....");
        //  openFeign-ribbon，客户端一般默认1秒钟
        return paymentFeignService.paymentFeignTimeout();
    }

}
