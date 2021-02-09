package org.server;

import cn.gasin.domain.Name;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableEurekaServer
@SpringBootApplication(proxyBeanMethods = false)
@EnableFeignClients
@EnableHystrix // 实际上就是 @EnableCircuitBreaker
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


    // 加入acurator, 暴露hystrix的endpoint, 收到
    /*
    /hystrix.stream 路径下
    ping:
        data: {"type":"HystrixCommand","name":"FeignClient#getName(String)","group":"ribbon-consumer","currentTime":1612880141071,"isCircuitBreakerOpen":true,"errorPercentage":100,"errorCount":7,"requestCount":7,"rollingCountBadRequests":0,"rollingCountCollapsedRequests":0,"rollingCountEmit":0,"rollingCountExceptionsThrown":0,"rollingCountFailure":0,"rollingCountFallbackEmit":0,"rollingCountFallbackFailure":0,"rollingCountFallbackMissing":0,"rollingCountFallbackRejection":0,"rollingCountFallbackSuccess":8,"rollingCountResponsesFromCache":0,"rollingCountSemaphoreRejected":0,"rollingCountShortCircuited":1,"rollingCountSuccess":0,"rollingCountThreadPoolRejected":0,"rollingCountTimeout":7,"currentConcurrentExecutionCount":0,"rollingMaxConcurrentExecutionCount":4,"latencyExecute_mean":0,"latencyExecute":{"0":0,"25":0,"50":0,"75":0,"90":0,"95":0,"99":0,"99.5":0,"100":0},"latencyTotal_mean":0,"latencyTotal":{"0":0,"25":0,"50":0,"75":0,"90":0,"95":0,"99":0,"99.5":0,"100":0},"propertyValue_circuitBreakerRequestVolumeThreshold":4,"propertyValue_circuitBreakerSleepWindowInMilliseconds":5000,"propertyValue_circuitBreakerErrorThresholdPercentage":50,"propertyValue_circuitBreakerForceOpen":false,"propertyValue_circuitBreakerForceClosed":false,"propertyValue_circuitBreakerEnabled":true,"propertyValue_executionIsolationStrategy":"THREAD","propertyValue_executionIsolationThreadTimeoutInMilliseconds":1000,"propertyValue_executionTimeoutInMilliseconds":1000,"propertyValue_executionIsolationThreadInterruptOnTimeout":true,"propertyValue_executionIsolationThreadPoolKeyOverride":null,"propertyValue_executionIsolationSemaphoreMaxConcurrentRequests":10,"propertyValue_fallbackIsolationSemaphoreMaxConcurrentRequests":10,"propertyValue_metricsRollingStatisticalWindowInMilliseconds":10000,"propertyValue_requestCacheEnabled":true,"propertyValue_requestLogEnabled":true,"reportingHosts":1,"threadPool":"ribbon-consumer"}
        data: {"type":"HystrixThreadPool","name":"ribbon-consumer","currentTime":1612880141071,"currentActiveCount":0,"currentCompletedTaskCount":7,"currentCorePoolSize":10,"currentLargestPoolSize":7,"currentMaximumPoolSize":10,"currentPoolSize":7,"currentQueueSize":0,"currentTaskCount":7,"rollingCountThreadsExecuted":2,"rollingMaxActiveThreads":0,"rollingCountCommandRejections":0,"propertyValue_queueSizeRejectionThreshold":5,"propertyValue_metricsRollingStatisticalWindowInMilliseconds":10000,"reportingHosts":1}
     */
    /***/
    @Bean
    public ServletRegistrationBean hystrixMetricsStreamServlet() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new HystrixMetricsStreamServlet());
        registration.addUrlMappings("/hystrix.stream");
        return registration;
    }


}
