package com.example.hello_springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("演示用系统 API 规格书")
                        .version("1.0.0")
                        .description("这里是详细设计书（DD）关联的 API 文档")
                        .license(new License().name("Apache 2.0")));
    }

}
