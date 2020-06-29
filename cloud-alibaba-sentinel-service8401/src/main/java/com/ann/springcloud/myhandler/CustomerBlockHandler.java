package com.ann.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.ann.springcloud.entities.CommonResult;

/**
 * @author longquan
 */
public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException e){
        return new CommonResult(4444,"自定义Global handlerException1....");
    }

    public static CommonResult handlerException2(BlockException e){
        return new CommonResult(4444,"自定义Global handlerException2....");
    }
}
