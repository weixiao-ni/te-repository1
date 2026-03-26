package com.example.hello_springboot.config.AdminExtractionBatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 用户数据保存Job配置类
 * 
 * 职责: 定义Spring Batch的Job，编排批处理流程
 * 功能: 将管理员用户数据导出到CSV文件
 * 组成: 包含一个或多个Step的执行流程
 * 监听: 使用UserPreserveJobListener监听Job生命周期
 */
@Configuration
public class UserPreserveJob {

    /**
     * 构建用户数据保存Job
     * 
     * @param jobRepository        Job元数据仓库（自动注入）
     * @param userPreserveStep     用户数据处理Step（自动注入）
     * @param jobListener          Job监听器（自动注入）← 新增参数
     * @return 配置完成的Job实例
     */
    @Bean
    public Job builderUserPreserveJob(
            JobRepository jobRepository,
            Step userPreserveStep,
            UserPreserveJobListener jobListener  // 新增: 注入监听器
    ) {
        return new JobBuilder("userPreserveJob", jobRepository)
                
                // ============================================
                // 注册Job监听器（新增）
                // ============================================
                .listener(jobListener)
                // 作用: 在Job开始前和结束后执行监听器的回调方法
                // 执行顺序:
                //   1. jobListener.beforeJob()
                //   2. Step执行...
                //   3. jobListener.afterJob()
                // 
                // 可以注册多个监听器:
                // .listener(listener1)
                // .listener(listener2)
                // 执行顺序: 注册顺序执行beforeJob，逆序执行afterJob
                
                // ============================================
                // 定义Job的执行流程
                // ============================================
                .start(userPreserveStep)
                
                // 可扩展: 添加更多Step
                // .next(step2)
                // .next(step3)
                
                .build();
    }
}