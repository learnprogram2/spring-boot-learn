package com.gasin.controller;

import cn.gasin.feign.IEurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/demo")
public class DemoController implements IEurekaClient {
    private final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Override
    @GetMapping("/{name}")
    public String getName(@PathVariable("name") String name) {
        logger.info("get on request: name is [{}]", name);

        return "hello " + name + ", you get from client2";
    }

    @GetMapping("/Hello/{name}")
    public String getHello(@PathVariable("name") String name) {
        logger.info("get on request: name is [{}]", name);

        return "hello " + name + ", you get from client1";
    }


}
