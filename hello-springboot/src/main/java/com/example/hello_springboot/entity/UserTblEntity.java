package com.example.hello_springboot.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  //（Lombok 库 组和注解）
@NoArgsConstructor //(Lombok 库无参构造注解)
@AllArgsConstructor //(Lombok 库全参构造注解)
@Entity
@Table(name = "USER_TBL") // JPA 映射的表名
public class UserTblEntity implements Serializable {
	public Long getUserCd() {
		return userCd;
	}

	public void setUserCd(Long userCd) {
		this.userCd = userCd;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRepwd() {
		return repwd;
	}

	public void setRepwd(String repwd) {
		this.repwd = repwd;
	}

	public String getFirstNameKana() {
		return firstNameKana;
	}

	public void setFirstNameKana(String firstNameKana) {
		this.firstNameKana = firstNameKana;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastNameKana() {
		return lastNameKana;
	}

	public void setLastNameKana(String lastNameKana) {
		this.lastNameKana = lastNameKana;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCountryZip() {
		return countryZip;
	}

	public void setCountryZip(String countryZip) {
		this.countryZip = countryZip;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}

	public String getPhotoAddress() {
		return photoAddress;
	}

	public void setPhotoAddress(String photoAddress) {
		this.photoAddress = photoAddress;
	}

	public LocalDateTime getRegistDate() {
		return registDate;
	}

	public void setRegistDate(LocalDateTime registDate) {
		this.registDate = registDate;
	}

	public String getRegistUser() {
		return registUser;
	}

	public void setRegistUser(String registUser) {
		this.registUser = registUser;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long
	serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_CD")
    private Long userCd;
    
    @Column(name = "AUTHORITY")
    private String authority;
    
    @Column(name = "COMPANY_ID")
    private String companyId;


    @Column(name = "USER_PWD", nullable = false)
    private String userPwd; // 数据库列名是 USER_PWD

    @Transient
    private String pwd;    // 对应 Postman 传入的 "pwd"，不映射到数据库
    
    @Transient
    private String repwd;  // 对应 Postman 传入的 "repwd"，不映射到数据库

    @Column(name = "FIRST_NAME_KANA")
    private String firstNameKana;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME_KANA")
    private String lastNameKana;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "SEX")
    private String sex;

    @Column(name = "COUNTRY_ZIP")
    private String countryZip;

    @Column(name = "USER_TEL")
    private String userTel;

    @Column(name = "USER_MAIL")
    private String userMail;

    @Column(name = "USER_PHOTO")
    private String userPhoto; // 数据库列名

    @Transient
    private String photoAddress; // 对应 Postman 的 "photoAddress"

    // --- 审计字段 (Spring Batch 练习中常用于筛选时间范围) ---
    
    @Column(name = "REGIST_DATE", updatable = false) //注册时间
    private LocalDateTime registDate;

    @Column(name = "REGIST_USER")
    private String registUser;
    
    @LastModifiedDate // 自动记录最后修改时间
    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;

    @Column(name = "USER_STATUS")
    private String userStatus;

    /**
     * 在数据进入 MyBatis/JPA 之前，同步 Postman 传入的特殊字段名   
     * public void syncFields() {
       * if (this.pwd != null) this.userPwd = this.pwd;
       * if (this.photoAddress != null) this.userPhoto = this.photoAddress;
    *}
    */
}