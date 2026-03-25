package com.example.hello_springboot.config.AdminExtractionBatch;

import java.util.Map;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import com.example.hello_springboot.entity.UserTblEntity;
import com.example.hello_springboot.repository.UserTblRepository;

@Configuration
public class UserPreserveItemReader {
	@Bean
	public RepositoryItemReader<UserTblEntity> jpaItemReader(UserTblRepository userTblRepository) {
		return new RepositoryItemReaderBuilder<UserTblEntity>().repository(userTblRepository) // 1. 指定 JPA 仓库
				.name("userItemReader")  // <-- 必须加上这一行，名字可以自定义，但不能为空
				.methodName("findByIsAdminTrue") // 2. 指定调用的方法名
				.pageSize(10) // 3. 每页读取数量，建议与 chunk 大小一致
				.sorts(Map.of("user_Cd", Sort.Direction.ASC)) // 4. 必须指定排序，否则分页会乱
				.build();
	}
}