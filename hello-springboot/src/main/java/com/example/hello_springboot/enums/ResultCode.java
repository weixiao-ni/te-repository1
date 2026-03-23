package com.example.hello_springboot.enums;

import lombok.Getter;

@Getter
public enum ResultCode {
	SUCCESS(200, "操作成功"),
    UNAUTHORIZED(404, "パラメータチェック失敗"),
    SYSTEM_ERROR(500, "操作失敗");
	private final Integer code;
    private final String message;

    // 枚举的构造函数默认就是 private
    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}