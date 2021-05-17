package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author Meng Yang
 * @create 2021-04-25 8:50
 */
public interface LoadBalancer {

    ServiceInstance instance(List<ServiceInstance> list);
}
