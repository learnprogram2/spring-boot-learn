package org.server.feign;

import cn.gasin.feign.IEurekaClient;

@org.springframework.cloud.openfeign.FeignClient(value = "ribbon-consumer",
        fallback = FallBackForEurekaClient.class)
public interface FeignClient extends IEurekaClient {


}
