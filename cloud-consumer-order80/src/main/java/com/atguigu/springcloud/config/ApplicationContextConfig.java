package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Meng Yang
 * @create 2021-04-21 21:14
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
//    @LoadBalanced //使用此注解赋予template负载均衡的能力
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
