package com.example.hello_springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration  //标记为配置类，工厂
public class RestClientConfig {
	@Bean  //声明spring容器管理方法返回new 构造的依赖类对象
	public RestTemplate  restTemplate() {  //配置类对象方法，半自动化构造bean
        return new RestTemplate(); //RestTemplate为springBoot内置模板导入api类
    }
}
