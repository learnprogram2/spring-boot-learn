package org.server.feign;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 降级实现
 */
@Component
@RequestMapping("order-service/fallback")
public class FallBackForEurekaClient implements FeignClient {
    @Override
    public String getName(String name) {
        // 这里实现降级逻辑
        System.out.println("eurekaClient.getName 接口降级了...");
        return "降级了";
    }

//    public static class FallBackFactory implements FallbackFactory {
//
//        @Override
//        public Object create(Throwable throwable) {
//            return new FallBackForEurekaClient();
//        }
//    }
}