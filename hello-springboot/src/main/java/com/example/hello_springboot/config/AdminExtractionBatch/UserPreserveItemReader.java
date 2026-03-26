package com.example.hello_springboot.config.AdminExtractionBatch;

import java.util.Map;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import com.example.hello_springboot.entity.UserTblEntity;
import com.example.hello_springboot.repository.UserTblRepository;

/**
 * 用户数据读取器配置类
 * 
 * 功能: 配置从数据库读取管理员用户数据的ItemReader
 * 数据源: UserTblRepository (Spring Data JPA)
 * 读取条件: isAdmin = true 的用户
 * 分页大小: 10条/页
 * 排序: 按userCd升序
 */
@Configuration
public class UserPreserveItemReader {

    /**
     * 创建JPA Repository数据读取器
     * 
     * @param userTblRepository 用户表Repository（自动注入）
     * @return 配置完成的RepositoryItemReader
     */
    @Bean
    public RepositoryItemReader<UserTblEntity> jpaItemReader(UserTblRepository userTblRepository) {
        
        return new RepositoryItemReaderBuilder<UserTblEntity>()
                // 指定数据源Repository
                .repository(userTblRepository)
                // Reader唯一标识（必须设置）
                .name("userItemReader")
                // 调用Repository的findByIsAdminTrue方法
                .methodName("findByIsAdminTrue")
                // 每页10条，建议与chunk size一致
                .pageSize(10)
                // 按userCd升序排列（使用Entity属性名）
                .sorts(Map.of("USER_CD", Sort.Direction.ASC))
                .build();
    }
}