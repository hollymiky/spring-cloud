package com.ann.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author longquan
 */
public interface LoadBalancer {

    /**
     * 获取提供服务的ServiceInstance
     *
     * @param serviceInstances 提供服务的list
     */
    ServiceInstance instance(List<ServiceInstance> serviceInstances);

}
