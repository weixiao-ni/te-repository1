package com.example.hello_springboot.DTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class UserTblEntity {
	@NotBlank(message = "権限は必須項目")
    @Size(max = 50)
    private String authority; // 権限プロパティ

    @NotBlank(message = "会社IDは必須項目")
    @Size(max = 10)
    private String companyId; // 会社ID

    @NotBlank(message = "住所は必須項目")
    @Size(max = 11)
    private String countryZip; // 住所

    @NotBlank(message = "氏名は必須項目")
    @Size(max = 10)
    private String firstName; // 氏名

    @NotBlank(message = "氏名(カナ)は必須項目")
    @Size(max = 10)
    private String firstNameKana; // 氏名(カナ)

    @NotBlank(message = "姓は必須項目")
    @Size(max = 10)
    private String lastName; // 姓

    @NotBlank(message = "姓(カナ)は必須項目")
    @Size(max = 10)
    private String lastNameKana; // 姓(カナ)

    @Size(max = 200)
    private String photoAddress; // 写真アドレス

    @NotBlank(message = "密码は必須項目")
    @Size(max = 200)
    private String pwd; // パスワード

    @NotBlank(message = "确认密码は必須項目")
    @Size(max = 100)
    private String repwd; // パスワード確認

    @NotBlank(message = "性别は必須項目")
    @Size(max = 1)
    private String sex; // 性別

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

	public String getCountryZip() {
		return countryZip;
	}

	public void setCountryZip(String countryZip) {
		this.countryZip = countryZip;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstNameKana() {
		return firstNameKana;
	}

	public void setFirstNameKana(String firstNameKana) {
		this.firstNameKana = firstNameKana;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastNameKana() {
		return lastNameKana;
	}

	public void setLastNameKana(String lastNameKana) {
		this.lastNameKana = lastNameKana;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getUserCd() {
		return userCd;
	}

	public void setUserCd(Integer userCd) {
		this.userCd = userCd;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	@Override
	public String toString() {
		return "UserTblEntity [authority=" + authority + ", companyId=" + companyId + ", countryZip=" + countryZip
				+ ", firstName=" + firstName + ", firstNameKana=" + firstNameKana + ", lastName=" + lastName
				+ ", lastNameKana=" + lastNameKana + ", userPhoto=" + photoAddress + ", pwd=" + pwd + ", repwd="
				+ repwd + ", sex=" + sex + ", userCd=" + userCd + ", userMail=" + userMail + ", userStatus="
				+ userStatus + ", userTel=" + userTel + "]";
	}

	public String getPhotoAddress() {
		return photoAddress;
	}

	public void setPhotoAddress(String photoAddress) {
		this.photoAddress = photoAddress;
	}

	@NotNull(message = "用户IDは必須項目")
    private Integer userCd; // ユーザーID

    @NotBlank(message = "邮箱は必須項目")
    @Size(max = 50)
    private String userMail; // ユーザーメール

    @Size(max = 20)
    private String userStatus; // ユーザー状態

    @NotBlank(message = "电话号码は必須項目")
    @Size(max = 15)
    private String userTel; // ユーザー電話番号
}
