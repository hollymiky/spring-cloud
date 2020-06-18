package com.ann.springcloud.service;

import com.ann.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author longquan
 */
public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);

}
