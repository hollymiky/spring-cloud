package com.ann.springcloud.service;

import com.ann.springcloud.entities.CommonResult;
import com.ann.springcloud.entities.Payment;
import org.springframework.stereotype.Service;

/**
 * @author longquan
 */
@Service
public class PaymentFallbackService implements PaymentService {

    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444,"服务降级处理返回，------ PaymentFallbackService ------",new Payment(id,"error"));
    }
}
