package com.ann.springcloud.controller;

import com.ann.springcloud.entities.CommonResult;
import com.ann.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author longquan
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap = new HashMap<>(16);

    static {
        hashMap.put(1L, new Payment(1L, "testData1"));
        hashMap.put(2L, new Payment(2L, "testData2"));
        hashMap.put(3L, new Payment(3L, "testData3"));
    }

    @GetMapping("/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        Payment payment = hashMap.get(id);
        return new CommonResult<>(200, "from mysql，serverPort：" + serverPort, payment);
    }

}
