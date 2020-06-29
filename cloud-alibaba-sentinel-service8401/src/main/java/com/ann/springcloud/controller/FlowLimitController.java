package com.ann.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author longquan
 */
@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
        /*try{
            TimeUnit.MILLISECONDS.sleep(800);
        }catch (Exception e){
            e.printStackTrace();
        }*/
        return "---------- testA ------------";
    }

    @GetMapping("/testB")
    public String testB(){
        log.info(Thread.currentThread().getName()+"\t"+"--- testB ---");
        return "---------- testB ------------";
    }

    /**
     * 测试异常比例限流
     */
    @GetMapping("/testC")
    public String testC(){
        log.info("testC 测试异常比例");
        int age = 10/0;
        return "------------ testC -----------";
    }

    /**
     * 测试RT限流
     */
    @GetMapping("/testD")
    public String testD(){
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info("testD 测试RT");
        return "------------ testD -----------";
    }

    /**
     * 测试异常数限流
     */
    @GetMapping("/testE")
    public String testE(){
        log.info("testE 测试异常数");
        int age = 10/0;
        return "------------ testE -----------";
    }

    /**
     * 测试热点限流
     */
    @GetMapping("/testHotKey")
    //  @SentinelResource 类似于 Hystrix中的@HystrixCommand注解，服务降级处理
    //  value 代表可以作为热点规则中的资源名，blockHandler 兜底方法
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2){
        //  Sentinel只负责在Sentinel控制台设置的错误，代码级的异常不处理
        //int i = 10/0;

        /**
         * @SentinelResource
         * 处理的是Sentinel控制台配置的违规情况，有blockHandler方法配置的兜底处理
         *
         * RuntimeException
         * 这是java运行时报出的异常，Sentinel不管
         *
         * 总结：
         * @SentinelResource 主管配置出错，运行出错该走异常就走异常
         *
         */
        return "--------- testHotKey -----------";
    }

    public String deal_testHotKey(String p1, String p2, BlockException e){
        //  Sentinel系统默认的提示：Blocked by Sentinel(flow limiting)
        return "--------- deal_testHotKey -----------";
    }
}
