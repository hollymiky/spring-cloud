package com.ann.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author longquan
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_ok(Integer id) {
        return "-----PaymentFallbackService fall back + paymentInfo_ok-------";
    }

    @Override
    public String paymentInfo_timeout(Integer id) {
        return "-----PaymentFallbackService fall back + paymentInfo_timeout";
    }
}
