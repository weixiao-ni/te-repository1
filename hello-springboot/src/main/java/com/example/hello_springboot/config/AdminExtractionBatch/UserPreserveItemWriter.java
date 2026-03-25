package com.example.hello_springboot.config.AdminExtractionBatch;

import com.example.hello_springboot.entity.UserTblEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.io.File;

@Configuration
public class UserPreserveItemWriter {

    private static final Logger log = LoggerFactory.getLogger(UserPreserveItemWriter.class);

    @Bean
    @StepScope
    public FlatFileItemWriter<UserTblEntity> csvUserWriter() {
        String dirPath = "D:\\outputs";
        String fileName = "user_preserve_" + System.currentTimeMillis() + ".csv";

        File directory = new File(dirPath);
        if (!directory.exists() && !directory.mkdirs()) {
            throw new IllegalStateException("目录创建失败: " + dirPath);
        }

        File fullPath = new File(directory, fileName);

        log.info("输出文件: {}", fullPath.getAbsolutePath());
        log.info("文件是否存在(open前): {}", fullPath.exists());

        return new FlatFileItemWriterBuilder<UserTblEntity>()
                .name("userItemWriter")
                .resource(new FileSystemResource(fullPath))
                .saveState(false)
                .shouldDeleteIfExists(true)
                .append(false)
                .delimited()
                .delimiter(",")
                .names(
                        "userCd", "companyId", "authority", "firstName", "lastName",
                        "userMail", "registDate", "userStatus"
                )
                .headerCallback(writer ->
                        writer.write("UserCD,CompanyID,Authority,FirstName,LastName,Email,RegistDate,Status"))
                .build();
    }
}