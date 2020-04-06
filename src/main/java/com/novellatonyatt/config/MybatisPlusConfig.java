package com.novellatonyatt.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Zhuang HaoTang
 * @Date: 2020/4/6 17:46
 * @Description:
 */
@Configuration
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 当请求的页面大于最大页时，如果为true则返回第一页的数据，否则继续请求最终返回空数组，默认为false。
        paginationInterceptor.setOverflow(false);
        return paginationInterceptor;
    }
}