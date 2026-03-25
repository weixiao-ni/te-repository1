package com.example.hello_springboot.config.AdminExtractionBatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class  UserPreserveJobScheduler {
	private static final Logger log = LoggerFactory.getLogger(UserPreserveJobScheduler.class);  // 手动创建 Logger 对象
    @Autowired
    private JobLauncher jobLauncher; //  专门负责跑 Job 的引擎
//JobLauncher类无需手动装配，手动装配可以调异步，或多数据库指定连接
    @Autowired
    private Job userPreserveJob; // 注入你刚才定义的那个 Job, 按标识符匹配

    // 每隔 5 分钟跑一次 (Cron 表达式：秒 分 时 日 月 周)
    @Scheduled(cron = "0 59 23 L * ?") 
    public void runUserPreserveJob() {
        try {
            // 每次运行都要给一个不同的参数（比如时间戳）
            // 否则 Spring Batch 会认为任务已完成，拒绝重复执行
            JobParameters params = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis()) 
                    .toJobParameters();
            JobExecution execution = jobLauncher.run(userPreserveJob, params);
            //System.out.println("Job 运行成功！");
            log.info("管理者抽出バッチ ：処理中{}", execution.getStatus());
            log.info("管理者抽出バッチ 操作成功");
        } catch (Exception e) {
            log.error("管理者抽出バッチ:エラーが発生しております ", e);
        }
    }
}