package com.ann.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author longquan
 *
 * 使用硬编码替代yml中gateway的配置信息
 */
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){

        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        // http://news.baidu.com/guonei
        routes.route("path_route_ann",r ->r.path("/guonei").uri("http://news.baidu.com/guonei")).build();

        return routes.build();
    }

    @Bean
    public RouteLocator customRouteLocator2(RouteLocatorBuilder routeLocatorBuilder){

        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        // http://news.baidu.com/guonei
        routes.route("path_route_ann2",r ->r.path("/guoji").uri("http://news.baidu.com/guoji")).build();

        return routes.build();
    }

}
