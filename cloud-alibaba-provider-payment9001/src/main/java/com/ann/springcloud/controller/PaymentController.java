package com.ann.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author longquan
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Value("${server.port}")
    public String serverPort;

    @GetMapping("/nacos/{id}")
    public String index(@PathVariable("id") Integer id) {
        return "nacos registry，serverPort： " + serverPort + "\t id：" + id;
    }

}
