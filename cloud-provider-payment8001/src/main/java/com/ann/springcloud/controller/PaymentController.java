package com.ann.springcloud.controller;

import com.ann.springcloud.entities.CommonResult;
import com.ann.springcloud.entities.Payment;
import com.ann.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("------插入结果：" + result);
        return result >0 ? new CommonResult(200,"插入数据库成功",result) : new CommonResult(400,"插入数据库失败",null);
    }

    @GetMapping("/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable Long id){
        Payment payment = paymentService.getPaymentById(id);
        int a = 10000/5;
        log.info("------获取结果：" + payment);
        return payment != null ? new CommonResult(200,"查询成功",payment) : new CommonResult(400,"没有对应记录，查询ID："+id,null);
    }

}
