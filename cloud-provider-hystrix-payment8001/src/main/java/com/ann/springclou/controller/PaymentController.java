package com.ann.springclou.controller;

import com.ann.springclou.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_ok(id);
        log.info("------result：" + result);
        return result;
    }

    @GetMapping("/hystrix/timeout/{id}")
    public String paymentInfo_timeout(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_fail(id);
        log.info("------result：" + result);
        return result;
    }
}
