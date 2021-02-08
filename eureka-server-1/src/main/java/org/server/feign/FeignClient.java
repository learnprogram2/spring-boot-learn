package org.server.feign;

import cn.gasin.feign.IEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@org.springframework.cloud.openfeign.FeignClient(value = "ribbon-consumer")
public interface FeignClient extends IEurekaClient {

}
