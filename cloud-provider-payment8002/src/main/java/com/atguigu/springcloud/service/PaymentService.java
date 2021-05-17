package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author Meng Yang
 * @create 2021-04-21 13:48
 */
public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") long id);
}
