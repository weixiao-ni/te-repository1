package com.example.hello_springboot.DTO;

import java.time.LocalDateTime;

/**
 * 用户数据导出传输对象（DTO）
 *
 * 用途：将内部实体 Entity 转换为“对外导出/报表用”的轻量对象，
 * 可以进行字段裁剪、别名转换、脱敏、值枚举翻译、日期格式化等。
 *
 * 建议：
 * - 跟 Writer 的表头列名一致（或有清晰转换规则）
 * - 不直接包含敏感原值（如密码），或仅包含脱敏版本
 * - 使用不可变或“只读导出”思想：导出对象通常不应该被业务修改太多，除非有明确需求
 */
public class UserExportDto {

    // 原始/核心业务字段（与表头对应）
    private String userCode;         // 用户代码（对应 UserTblEntity.userCd）
    private String companyCode;      // 公司代码（对应 UserTblEntity.companyId）
    private String authorityText;    // 权限中文名（通常由 Processor 转换，如普通/管理员）
    private String fullName;         // 姓名合成（姓+名）
    private String email;            // 邮箱（建议脱敏后的）
    private LocalDateTime registrationDate; // 注册日期（建议转换为易读格式，或在写入时格式化）
    private String statusText;       // 状态中文名（如“活跃/停用”，通常由 Processor 转换）

    // 可选：导出元信息字段（便于审计/对账）
    private LocalDateTime exportTime;   // 导出时间（通常由 Processor 设置）
    private String exportBatchId;       // 导出批次ID（可从 JobParameters/ExecutionContext 获取）

    // 构造函数（方便创建与转换）
    public UserExportDto() {
    }

    public UserExportDto(String userCode, String companyCode, String authorityText,
                         String fullName, String email, LocalDateTime registrationDate,
                         String statusText) {
        this.userCode = userCode;
        this.companyCode = companyCode;
        this.authorityText = authorityText;
        this.fullName = fullName;
        this.email = email;
        this.registrationDate = registrationDate;
        this.statusText = statusText;
    }

    // Getter / Setter
    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getAuthorityText() {
        return authorityText;
    }

    public void setAuthorityText(String authorityText) {
        this.authorityText = authorityText;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public LocalDateTime getExportTime() {
        return exportTime;
    }

    public void setExportTime(LocalDateTime exportTime) {
        this.exportTime = exportTime;
    }

    public String getExportBatchId() {
        return exportBatchId;
    }

    public void setExportBatchId(String exportBatchId) {
        this.exportBatchId = exportBatchId;
    }

    // 便于调试的 toString（可以按你需要裁剪敏感信息）
    @Override
    public String toString() {
        return String.format("UserExportDto{userCode=%s, companyCode=%s, auth=%s, name=%s, email=%s, reg=%s, status=%s, exportTime=%s}",
                             userCode, companyCode, authorityText, fullName, email,
                             registrationDate, statusText, exportTime);
    }
}