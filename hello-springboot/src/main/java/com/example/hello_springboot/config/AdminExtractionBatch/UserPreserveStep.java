package com.example.hello_springboot.config.AdminExtractionBatch;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.hello_springboot.entity.UserTblEntity;

@Configuration
public class UserPreserveStep {

    @Bean
    public Step builderUserPreserveStep(
            JobRepository jobRepository, 
            PlatformTransactionManager transactionManager,
            RepositoryItemReader<UserTblEntity> jpaItemReader,
            UserPreserveItemProcessor userPreserveItemProcessor, 
            FlatFileItemWriter<UserTblEntity> flatFileItemWriter) {
        return new StepBuilder("userPreserveStep", jobRepository)
                .<UserTblEntity, UserTblEntity>chunk(10, transactionManager) 
                .reader(jpaItemReader) 
                .processor(userPreserveItemProcessor) 
                .writer(flatFileItemWriter) 
                .build();
    }
}