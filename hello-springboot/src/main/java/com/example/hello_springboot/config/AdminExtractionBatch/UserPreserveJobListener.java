package com.example.hello_springboot.config.AdminExtractionBatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

/**
 * 用户数据保存Job监听器
 * 
 * 职责: 监听Job的生命周期事件，在Job开始前和结束后执行自定义逻辑
 * 实现: JobExecutionListener接口
 * 
 * 应用场景:
 * - 记录Job执行日志
 * - 发送执行通知（邮件/短信）
 * - 资源初始化和清理
 * - 执行统计和监控
 * - 异常处理和告警
 */
@Component  // 将此类注册为Spring Bean，使其能被自动注入
public class UserPreserveJobListener implements JobExecutionListener {

    // 日志记录器，用于输出Job执行信息
    private static final Logger log = LoggerFactory.getLogger(UserPreserveJobListener.class);
    
    // 日期时间格式化器
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Job执行前的回调方法
     * 
     * @param jobExecution Job执行上下文，包含Job的所有执行信息
     * 
     * 执行时机: Job开始执行之前（所有Step执行之前）
     * 
     * 常见用途:
     * - 记录Job开始时间
     * - 初始化资源（数据库连接、文件句柄等）
     * - 验证前置条件
     * - 发送开始通知
     * - 设置Job级别的上下文参数
     */
    @Override
    public void beforeJob(JobExecution jobExecution) {
        // ============================================
        // 1. 获取基本信息
        // ============================================
        String jobName = jobExecution.getJobInstance().getJobName();
        // 作用: 获取Job的逻辑名称（在JobBuilder中定义的名称）
        // 示例值: "userPreserveJob"
        
        Long jobInstanceId = jobExecution.getJobInstance().getInstanceId();
        // 作用: 获取Job实例ID（数据库中的唯一标识）
        // 说明: 相同参数的Job只会有一个实例ID
        
        Long jobExecutionId = jobExecution.getId();
        // 作用: 获取Job执行ID（每次执行都不同）
        // 说明: 同一个Job实例可以有多次执行（如重试）
        
        // ============================================
        // 2. 获取Job参数
        // ============================================
        String jobParameters = jobExecution.getJobParameters().toString();
        // 作用: 获取启动Job时传入的参数
        // 示例值: "{time=1774486247103}"
        
        // ============================================
        // 3. 获取开始时间
        // ============================================
        LocalDateTime startTime = jobExecution.getStartTime();
        // 作用: 获取Job的开始时间
        // 注意: 在beforeJob中可能还未设置，需要判空
        
        String startTimeStr = startTime != null 
                ? startTime.format(FORMATTER) 
                : LocalDateTime.now().format(FORMATTER);
        
        // ============================================
        // 4. 输出日志
        // ============================================
        log.info("╔══════════════════════════════════════════════════════════════╗");
        log.info("║                    JOB 开始执行                               ║");
        log.info("╠══════════════════════════════════════════════════════════════╣");
        log.info("║ Job名称        : {}", jobName);
        log.info("║ Job实例ID      : {}", jobInstanceId);
        log.info("║ Job执行ID      : {}", jobExecutionId);
        log.info("║ Job参数        : {}", jobParameters);
        log.info("║ 开始时间       : {}", startTimeStr);
        log.info("║ 执行线程       : {}", Thread.currentThread().getName());
        log.info("╚══════════════════════════════════════════════════════════════╝");
        
        // ============================================
        // 5. 可选：设置ExecutionContext数据（供后续Step使用）
        // ============================================
        jobExecution.getExecutionContext().putString("startedBy", "UserPreserveJobListener");
        jobExecution.getExecutionContext().putLong("jobStartTimestamp", System.currentTimeMillis());
        // 作用: 在Job上下文中存储数据，可在Step中读取
        // 用途: 传递Job级别的共享数据
    }

    /**
     * Job执行后的回调方法
     * 
     * @param jobExecution Job执行上下文，包含执行结果信息
     * 
     * 执行时机: Job结束后（所有Step执行完成后，无论成功还是失败）
     * 
     * 常见用途:
     * - 记录Job结束时间和执行时长
     * - 统计处理结果（读取数/写入数/跳过数）
     * - 清理资源
     * - 发送完成通知
     * - 处理失败情况（告警/重试逻辑）
     * - 生成执行报告
     */
    @Override
    public void afterJob(JobExecution jobExecution) {
        // ============================================
        // 1. 获取基本信息
        // ============================================
        String jobName = jobExecution.getJobInstance().getJobName();
        Long jobExecutionId = jobExecution.getId();
        
        // ============================================
        // 2. 获取执行状态
        // ============================================
        BatchStatus status = jobExecution.getStatus();
        // 作用: 获取Job的执行状态
        // 可能值: COMPLETED, FAILED, STOPPED, ABANDONED, UNKNOWN
        
        String exitCode = jobExecution.getExitStatus().getExitCode();
        // 作用: 获取退出码
        // 可能值: COMPLETED, FAILED, STOPPED, NOOP 等
        
        String exitDescription = jobExecution.getExitStatus().getExitDescription();
        // 作用: 获取退出描述（通常在失败时包含错误信息）
        
        // ============================================
        // 3. 获取时间信息
        // ============================================
        LocalDateTime startTime = jobExecution.getStartTime();
        LocalDateTime endTime = jobExecution.getEndTime();
        
        String startTimeStr = startTime != null ? startTime.format(FORMATTER) : "N/A";
        String endTimeStr = endTime != null ? endTime.format(FORMATTER) : "N/A";
        
        // 计算执行时长
        String durationStr = "N/A";
        if (startTime != null && endTime != null) {
            Duration duration = Duration.between(startTime, endTime);
            long minutes = duration.toMinutes();
            long seconds = duration.toSecondsPart();
            long millis = duration.toMillisPart();
            durationStr = String.format("%d分 %d秒 %d毫秒", minutes, seconds, millis);
        }
        
        // ============================================
        // 4. 获取Step执行统计
        // ============================================
        Collection<StepExecution> stepExecutions = jobExecution.getStepExecutions();
        // 作用: 获取所有Step的执行信息
        
        long totalReadCount = 0;    // 总读取数
        long totalWriteCount = 0;   // 总写入数
        long totalSkipCount = 0;    // 总跳过数
        long totalCommitCount = 0;  // 总提交数
        
        StringBuilder stepDetails = new StringBuilder();
        
        for (StepExecution stepExecution : stepExecutions) {
            String stepName = stepExecution.getStepName();
            long readCount = stepExecution.getReadCount();
            long writeCount = stepExecution.getWriteCount();
            long skipCount = stepExecution.getSkipCount();
            long commitCount = stepExecution.getCommitCount();
            BatchStatus stepStatus = stepExecution.getStatus();
            
            totalReadCount += readCount;
            totalWriteCount += writeCount;
            totalSkipCount += skipCount;
            totalCommitCount += commitCount;
            
            stepDetails.append(String.format(
                    "\n║   [%s] 状态=%s, 读取=%d, 写入=%d, 跳过=%d, 提交=%d",
                    stepName, stepStatus, readCount, writeCount, skipCount, commitCount
            ));
        }
        
        // ============================================
        // 5. 输出日志
        // ============================================
        log.info("╔══════════════════════════════════════════════════════════════╗");
        log.info("║                    JOB 执行完成                               ║");
        log.info("╠══════════════════════════════════════════════════════════════╣");
        log.info("║ Job名称        : {}", jobName);
        log.info("║ Job执行ID      : {}", jobExecutionId);
        log.info("║ 执行状态       : {}", status);
        log.info("║ 退出码         : {}", exitCode);
        log.info("║ 开始时间       : {}", startTimeStr);
        log.info("║ 结束时间       : {}", endTimeStr);
        log.info("║ 执行时长       : {}", durationStr);
        log.info("╠══════════════════════════════════════════════════════════════╣");
        log.info("║ 【执行统计】");
        log.info("║ 总读取数       : {}", totalReadCount);
        log.info("║ 总写入数       : {}", totalWriteCount);
        log.info("║ 总跳过数       : {}", totalSkipCount);
        log.info("║ 总提交数       : {}", totalCommitCount);
        log.info("╠══════════════════════════════════════════════════════════════╣");
        log.info("║ 【Step详情】{}", stepDetails);
        log.info("╚══════════════════════════════════════════════════════════════╝");
        
        // ============================================
        // 6. 根据状态执行不同操作
        // ============================================
        if (status == BatchStatus.COMPLETED) {
            // Job成功完成
            log.info("✅ Job [{}] 执行成功！共处理 {} 条数据", jobName, totalWriteCount);
            
            // TODO: 发送成功通知
            // sendSuccessNotification(jobName, totalWriteCount);
            
        } else if (status == BatchStatus.FAILED) {
            // Job执行失败
            log.error("❌ Job [{}] 执行失败！", jobName);
            log.error("失败原因: {}", exitDescription);
            
            // 获取失败的异常信息
            for (Throwable exception : jobExecution.getAllFailureExceptions()) {
                log.error("异常信息: ", exception);
            }
            
            // TODO: 发送失败告警
            // sendFailureAlert(jobName, exitDescription);
            
        } else if (status == BatchStatus.STOPPED) {
            // Job被停止
            log.warn("⚠️ Job [{}] 被手动停止", jobName);
            
        } else {
            // 其他状态
            log.warn("⚠️ Job [{}] 结束，状态: {}", jobName, status);
        }
        
        // ============================================
        // 7. 清理资源
        // ============================================
        // TODO: 清理临时文件、关闭连接等
        // cleanup();
    }
}