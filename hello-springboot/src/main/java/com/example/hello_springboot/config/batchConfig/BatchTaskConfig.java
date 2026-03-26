package com.example.hello_springboot.config.batchConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration // 必须加上这个注解，Spring 才会扫描并加载它
public class BatchTaskConfig {

    @Bean
    public TaskExecutor batchTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        
        // 1. 下限（核心线程数）：线程池中始终保持存活的线程数
        executor.setCorePoolSize(5); 
        
        // 2. 上限（最大线程数）：当等待池满了以后，最多能开启的线程数
        executor.setMaxPoolSize(10); 
        
        // 3. 等待池容量（任务队列）：核心线程忙时，任务排队的地方
        executor.setQueueCapacity(50); 
        
        // 设置线程名称前缀，方便在日志中排查问题
        executor.setThreadNamePrefix("Batch-Exp-");
        
        // 记得初始化
        executor.initialize(); 
        
        return executor;
    }
}