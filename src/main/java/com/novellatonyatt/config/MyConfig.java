package com.novellatonyatt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: Zhuang HaoTang
 * @create: 2020-04-20 16:36
 * @description:
 */
@Component
@ConfigurationProperties(prefix = "inf")
@Data
public class MyConfig {

    private String username;

    private String password;

    private Integer age;

}
