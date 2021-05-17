package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.impl.LoadBalancerImpl;
import jdk.nashorn.internal.ir.debug.ClassHistogramElement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author Meng Yang
 * @create 2021-04-21 21:07
 */
@RestController
@Slf4j
public class OrderController {

//    private static final String PAYMENT_URL="http://localhost:8001";
    private static final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalancerImpl balancer;
    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id") long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    @GetMapping("/consumer/payment/getEntity/{id}")
    public CommonResult getPayment2(@PathVariable("id") long id){
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
            if(forEntity.getStatusCode().is2xxSuccessful()){
                return forEntity.getBody();
            }else {
                return new CommonResult(444,"操作失败");
            }
    }

    @GetMapping("/consumer/payment/createEntity")
    public CommonResult<Payment> createEntity(Payment payment){
        ResponseEntity<CommonResult> responseEntity = restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        if(responseEntity.getStatusCode().is2xxSuccessful()){
            return responseEntity.getBody();
        }else {
            return new CommonResult<>(444,"新增数据失败");
        }
    }

    @GetMapping("/comsumer/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        ServiceInstance instance = balancer.instance(instances);
        URI uri = instance.getUri();
        System.out.println("==============="+instance);
        System.out.println("********************=========="+uri);
        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }
}
