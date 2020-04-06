package com.novellatonyatt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: Zhuang HaoTang
 * @Date: 2020/4/5 16:25
 * @Description:
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * http://localhost:8080/springboot-demo/swagger-ui.html
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.novellatonyatt.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("SpringBoot-Demo")
                        .description("SpringBoot-Demo Swagger")
                        .version("2.0")
                        .build());
    }

}
