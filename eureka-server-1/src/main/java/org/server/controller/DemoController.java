package org.server.controller;

import ch.qos.logback.core.spi.ContextAware;
import cn.gasin.domain.Name;
import org.server.feign.FeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/server")
public class DemoController implements ApplicationContextAware {
    private final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private FeignClient feignClient;

    @GetMapping("/{name}")
    public String getName(@PathVariable("name") String name) {
        logger.info("get on request: name is [{}]", name);

        return feignClient.getName(name);
    }


    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        System.out.println("=======================================");
        this.applicationContext = context;
        Name name = context.getBean(Name.class);
        System.out.println(name.getName());
    }

}
