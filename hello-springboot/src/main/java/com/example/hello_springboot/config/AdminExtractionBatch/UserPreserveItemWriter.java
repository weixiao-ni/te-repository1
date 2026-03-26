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

/**
 * 用户数据CSV导出Writer配置类
 * 职责: 为Spring Batch提供StepScope的FlatFileItemWriter Bean
 */
@Configuration  // 标识为Spring配置类,允许定义@Bean方法
public class UserPreserveItemWriter {

    // 创建日志记录器,用于跟踪Writer的创建和配置过程
    private static final Logger log = LoggerFactory.getLogger(UserPreserveItemWriter.class);

    /**
     * 创建CSV文件写入器Bean
     * @return 配置完成的FlatFileItemWriter实例
     * 
     * 关键注解:
     * @Bean - 将方法返回值注册为Spring容器管理的Bean
     * @StepScope - 延迟实例化,每次Step执行时创建新实例,支持动态参数
     */
    @Bean
    @StepScope  // 确保每次Job执行时都创建新的Writer实例,避免状态污染
    public FlatFileItemWriter<UserTblEntity> csvUserWriter() {
        // ========== 第一部分: Bean创建开始标记 ==========
        log.info("========== [csvUserWriter] StepScope Bean 创建开始 ==========");

        // ========== 第二部分: 定义输出路径参数 ==========
        String dirPath = "D:\\outputs";  // 定义输出目录的绝对路径
        String fileName = "user_preserve_" + System.currentTimeMillis() + ".csv";  // 使用时间戳生成唯一文件名,避免覆盖

        // ========== 第三部分: 创建File对象用于路径操作 ==========
        File directory = new File(dirPath);  // 创建目录的File对象,用于检查和创建目录
        File fullPath = new File(directory, fileName);  // 创建完整文件路径的File对象,结合目录和文件名

        // ========== 第四部分: 记录基本信息(时间/线程/路径) ==========
        log.info("[Writer初始化] 当前时间: {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));  // 记录Bean创建的精确时间
        log.info("[Writer初始化] 线程名: {}", Thread.currentThread().getName());  // 记录执行线程名,验证是否在正确的线程中执行
        log.info("[Writer初始化] 输出目录: {}", directory.getAbsolutePath());  // 记录目录的绝对路径,确认路径正确性
        log.info("[Writer初始化] 输出文件名: {}", fileName);  // 记录生成的文件名,包含时间戳
        log.info("[Writer初始化] 输出文件完整路径: {}", fullPath.getAbsolutePath());  // 记录完整的文件绝对路径

        // ========== 第五部分: 检查目录的存在性和权限 ==========
        log.info("[目录检查] exists = {}", directory.exists());  // 检查目录是否已存在
        log.info("[目录检查] isDirectory = {}", directory.isDirectory());  // 验证路径是目录而非文件
        log.info("[目录检查] canRead = {}", directory.canRead());  // 检查是否有读权限
        log.info("[目录检查] canWrite = {}", directory.canWrite());  // 检查是否有写权限(最关键)

        // ========== 第六部分: 目录不存在时创建目录 ==========
        if (!directory.exists()) {  // 如果目录不存在
            log.warn("[目录检查] 目录不存在，准备创建目录: {}", directory.getAbsolutePath());  // 警告级别记录,因为这是异常情况
            boolean created = directory.mkdirs();  // 创建多级目录(如果需要),返回是否成功
            log.info("[目录检查] mkdirs() 结果: {}", created);  // 记录创建结果

            if (!created) {  // 如果创建失败
                log.error("[目录检查] 目录创建失败: {}", directory.getAbsolutePath());  // 错误级别记录失败原因
                throw new IllegalStateException("目录创建失败: " + dirPath);  // 抛出异常,阻止继续执行,因为无法写入文件
            }
        }

        // ========== 第七部分: 检查文件状态和父目录权限 ==========
        log.info("[文件检查] 文件是否存在(open前): {}", fullPath.exists());  // 检查文件是否已存在(通常不应该存在)
        log.info("[文件检查] 文件父目录: {}", fullPath.getParent());  // 记录父目录路径,用于调试
        log.info("[文件检查] 父目录存在: {}", fullPath.getParentFile() != null && fullPath.getParentFile().exists());  // 再次确认父目录存在
        log.info("[文件检查] 父目录可写: {}", fullPath.getParentFile() != null && fullPath.getParentFile().canWrite());  // 确认可以在父目录中创建文件

        // ========== 第八部分: 如果文件已存在,记录详细信息 ==========
        if (fullPath.exists()) {  // 如果文件已存在(通常因为时间戳重复或之前运行遗留)
            log.warn("[文件检查] 目标文件已存在，shouldDeleteIfExists(true) 会在 writer open 时删除该文件");  // 提示将被删除
            log.info("[文件检查] 已存在文件 canRead = {}", fullPath.canRead());  // 检查现有文件的读权限
            log.info("[文件检查] 已存在文件 canWrite = {}", fullPath.canWrite());  // 检查现有文件的写权限
            log.info("[文件检查] 已存在文件 length = {}", fullPath.length());  // 记录现有文件大小
        }

        // ========== 第九部分: 记录即将应用的Writer配置参数 ==========
        log.info("[Writer配置] name = userItemWriter");  // Writer的逻辑名称,用于Spring Batch内部识别
        log.info("[Writer配置] resource = {}", fullPath.getAbsolutePath());  // 输出资源的完整路径
        log.info("[Writer配置] saveState = false");  // 不保存状态到ExecutionContext,适合无状态批处理
        log.info("[Writer配置] shouldDeleteIfExists = true");  // 如果文件存在则删除,确保全新写入
        log.info("[Writer配置] append = false");  // 不追加模式,每次都覆盖写入
        log.info("[Writer配置] delimiter = ','");  // CSV字段分隔符
        log.info("[Writer配置] header = UserCD,CompanyID,Authority,FirstName,LastName,Email,RegistDate,Status");  // CSV表头内容

        // ========== 第十部分: 使用Builder模式构建FlatFileItemWriter ==========
        FlatFileItemWriter<UserTblEntity> writer = new FlatFileItemWriterBuilder<UserTblEntity>()
                .name("userItemWriter")  // 设置Writer的名称,用于日志和监控
                .resource(new FileSystemResource(fullPath))  // 指定文件系统资源,封装文件路径
                .saveState(false)  // 禁用状态保存,提高性能,适合一次性导出
                .shouldDeleteIfExists(true)  // 启用文件存在时删除,防止追加到旧数据
                .append(false)  // 明确禁用追加模式,确保全新写入
                .delimited()  // 使用分隔符模式(CSV格式)
                .delimiter(",")  // 设置逗号为字段分隔符
                .names(  // 指定要写入的Entity字段名,按顺序映射到CSV列
                        "userCd",       // 用户代码
                        "companyId",    // 公司ID
                        "authority",    // 权限级别
                        "firstName",    // 名
                        "lastName",     // 姓
                        "userMail",     // 邮箱
                        "registDate",   // 注册日期
                        "userStatus"    // 用户状态
                )
                .headerCallback(w ->  // 设置表头回调,在文件开始时写入一次
                        w.write("UserCD,CompanyID,Authority,FirstName,LastName,Email,RegistDate,Status"))  // 写入自定义表头
                .build();  // 构建最终的Writer实例

        // ========== 第十一部分: Bean创建完成标记 ==========
        log.info("========== [csvUserWriter] StepScope Bean 创建完成 ==========");

        // ========== 第十二部分: 返回配置完成的Writer ==========
        return writer;  // 返回给Spring容器管理,供Step使用
    }
}