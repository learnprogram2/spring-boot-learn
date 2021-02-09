package cn.gasin.feign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/demo")
public interface IEurekaClient {
    @GetMapping("/{name}")
    public String getName(@PathVariable("name") String name) ;

}
