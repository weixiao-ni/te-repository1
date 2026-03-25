package com.example.hello_springboot;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling 
@SpringBootApplication //主启动类，入口注解
//重点 1：JPA 只扫描 repository 包
@EnableJpaRepositories(basePackages = "com.example.hello_springboot.repository")
//重点 2：MyBatis 只扫描 mapper 包
@MapperScan("com.example.hello_springboot.mapper")
public class HelloSpringbootApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloSpringbootApplication.class, args);
    }
}
