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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class UserPreserveItemWriter {

    private static final Logger log = LoggerFactory.getLogger(UserPreserveItemWriter.class);

    @Bean
    @StepScope
    public FlatFileItemWriter<UserTblEntity> csvUserWriter() {
        log.info("========== [csvUserWriter] StepScope Bean 创建开始 ==========");

        String dirPath = "D:\\outputs";
        String fileName = "user_preserve_" + System.currentTimeMillis() + ".csv";

        File directory = new File(dirPath);
        File fullPath = new File(directory, fileName);

        log.info("[Writer初始化] 当前时间: {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        log.info("[Writer初始化] 线程名: {}", Thread.currentThread().getName());
        log.info("[Writer初始化] 输出目录: {}", directory.getAbsolutePath());
        log.info("[Writer初始化] 输出文件名: {}", fileName);
        log.info("[Writer初始化] 输出文件完整路径: {}", fullPath.getAbsolutePath());

        log.info("[目录检查] exists = {}", directory.exists());
        log.info("[目录检查] isDirectory = {}", directory.isDirectory());
        log.info("[目录检查] canRead = {}", directory.canRead());
        log.info("[目录检查] canWrite = {}", directory.canWrite());

        if (!directory.exists()) {
            log.warn("[目录检查] 目录不存在，准备创建目录: {}", directory.getAbsolutePath());
            boolean created = directory.mkdirs();
            log.info("[目录检查] mkdirs() 结果: {}", created);

            if (!created) {
                log.error("[目录检查] 目录创建失败: {}", directory.getAbsolutePath());
                throw new IllegalStateException("目录创建失败: " + dirPath);
            }
        }

        log.info("[文件检查] 文件是否存在(open前): {}", fullPath.exists());
        log.info("[文件检查] 文件父目录: {}", fullPath.getParent());
        log.info("[文件检查] 父目录存在: {}", fullPath.getParentFile() != null && fullPath.getParentFile().exists());
        log.info("[文件检查] 父目录可写: {}", fullPath.getParentFile() != null && fullPath.getParentFile().canWrite());

        if (fullPath.exists()) {
            log.warn("[文件检查] 目标文件已存在，shouldDeleteIfExists(true) 会在 writer open 时删除该文件");
            log.info("[文件检查] 已存在文件 canRead = {}", fullPath.canRead());
            log.info("[文件检查] 已存在文件 canWrite = {}", fullPath.canWrite());
            log.info("[文件检查] 已存在文件 length = {}", fullPath.length());
        }

        log.info("[Writer配置] name = userItemWriter");
        log.info("[Writer配置] resource = {}", fullPath.getAbsolutePath());
        log.info("[Writer配置] saveState = false");
        log.info("[Writer配置] shouldDeleteIfExists = true");
        log.info("[Writer配置] append = false");
        log.info("[Writer配置] delimiter = ','");
        log.info("[Writer配置] header = UserCD,CompanyID,Authority,FirstName,LastName,Email,RegistDate,Status");

        FlatFileItemWriter<UserTblEntity> writer = new FlatFileItemWriterBuilder<UserTblEntity>()
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
                .headerCallback(w ->
                        w.write("UserCD,CompanyID,Authority,FirstName,LastName,Email,RegistDate,Status"))
                .build();

        log.info("========== [csvUserWriter] StepScope Bean 创建完成 ==========");

        return writer;
    }
}