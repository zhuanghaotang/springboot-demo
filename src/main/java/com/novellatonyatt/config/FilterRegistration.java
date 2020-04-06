package com.novellatonyatt.config;

import com.google.common.collect.Maps;
import com.novellatonyatt.filter.AccessFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @Author: Zhuang HaoTang
 * @Date: 2020/4/5 16:50
 * @Description:
 */
@Configuration
public class FilterRegistration {

    /**
     * 访问过滤器
     */
    @Bean
    public FilterRegistrationBean<AccessFilter> registerAccessFilter() {
        FilterRegistrationBean<AccessFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AccessFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        Map<String, String> param = Maps.newHashMap();
        param.put("param1", "value1");
        param.put("param2", "value2");
        param.put("param3", "value3");
        registrationBean.setInitParameters(param);
        return registrationBean;
    }


}
