package cn.gasin.config;

import com.netflix.zuul.ZuulFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class DemoConfig {
    /** 这种map<name, bean>的注入可以把全部的zuulFilter都注入进来 */
    @Autowired
    private Map<String, ZuulFilter> filters;

    public void setFilters(Map<String, ZuulFilter> filters) {
        this.filters = filters;
    }

}
