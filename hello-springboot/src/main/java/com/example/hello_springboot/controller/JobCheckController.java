package com.example.hello_springboot.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobCheckController {
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job userPreserveJob; // 对应你配置的 Job Bean

    @GetMapping("/run")
    public String runJob() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(userPreserveJob, params);
        return "Job started!";
    }
}