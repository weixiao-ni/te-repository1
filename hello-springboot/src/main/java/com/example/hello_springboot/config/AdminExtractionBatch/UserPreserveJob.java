package com.example.hello_springboot.config.AdminExtractionBatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserPreserveJob {

	@Bean
	public Job  builderUserPreserveJob(JobRepository jobRepository,Step userPreserveStep) {
	return new JobBuilder("userPreserveJob", jobRepository)
			.start(userPreserveStep) 
			// .next(step2)
			.build();
	}
}
