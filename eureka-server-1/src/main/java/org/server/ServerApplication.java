package org.server;

import cn.gasin.domain.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@EnableEurekaServer
@SpringBootApplication(proxyBeanMethods = false)
@EnableFeignClients
public class ServerApplication {


    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class);
    }

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @ConditionalOnMissingBean
    @Bean
    public Name getName2() {
        return new Name("@Bean2");
    }

    @ConditionalOnMissingBean
    @Bean
    public Name getName() {
        return new Name("@Bean");
    }

}
