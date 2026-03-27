package com.example.hello_springboot;

import java.util.Arrays;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling 
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.hello_springboot.repository")
@MapperScan("com.example.hello_springboot.mapper")
public class HelloSpringbootApplication {
    
    private static final Logger log = LoggerFactory.getLogger(HelloSpringbootApplication.class);
    
    public static void main(String[] args) {
        // 只启动一次！
        ConfigurableApplicationContext context = SpringApplication.run(HelloSpringbootApplication.class, args);
        
        // 打印当前激活的环境
        String[] profiles = context.getEnvironment().getActiveProfiles();
        System.out.println("=========================================");
        System.out.println("当前激活环境: " + Arrays.toString(profiles));
        System.out.println("=========================================");
        
        // 测试日志输出（确保日志文件生成）
        log.debug("DEBUG 级别日志测试");
        log.info("INFO 级别日志测试 - 应用启动成功");
        log.warn("WARN 级别日志测试");
        log.error("ERROR 级别日志测试");
        
        System.out.println("请检查日志文件是否生成！");
    }
}