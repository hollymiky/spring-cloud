package com.ann.springcloud;

import com.ann.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author longquan
 */
//  Eureka Client
@EnableEurekaClient
@SpringBootApplication
//  name 当前客户端需要访问的微服务名称，configuration 负载均衡策略
// @RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MySelfRule.class)
public class OrderMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class);
    }
}
