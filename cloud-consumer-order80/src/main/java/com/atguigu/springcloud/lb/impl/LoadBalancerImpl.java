package com.atguigu.springcloud.lb.impl;

import com.atguigu.springcloud.lb.LoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Meng Yang
 * @create 2021-04-25 8:52
 */
@Component
public class LoadBalancerImpl implements LoadBalancer {

    AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement(){
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current > Integer.MAX_VALUE ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current,next));
        System.out.println("*********第"+ next + "次访问**********");
        return  next;
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> list) {
        int index = this.getAndIncrement() % list.size();
        return list.get(index);
    }
}
