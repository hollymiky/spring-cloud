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

    @Bean
    @LoadBalanced //  一定切记加上这个注解，不然的话83找到了对应的服务提供者名称，但是实例超过1， 它不知道用什么算法去进行负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
