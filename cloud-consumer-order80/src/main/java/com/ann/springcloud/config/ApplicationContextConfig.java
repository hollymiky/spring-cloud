package com.ann.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author longquan
 */
@Configuration
public class ApplicationContextConfig {

    /**
     *  @LoadBalanced 开启RestTemplate的负载均衡功能
     */
    @Bean
    // 注释掉，自己手写一个负载均衡算法
    // @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
