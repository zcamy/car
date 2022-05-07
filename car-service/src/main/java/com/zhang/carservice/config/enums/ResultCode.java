package com.zhang.carservice.config.enums;

import lombok.Data;

/**
 * 返回状态
 */
public enum ResultCode {
	/* 成功状态码 */
	SUCCESS(0, "success",null),

	/* 系统错误码 */
	SYSTEM_INNER_ERROR(60000, "error","系统错误"),

	/* 请求值错误*/
	REQUEST_VALUE_ERROR(60000, "error","请求值错误"),

	/* 映射错误*/
	MAPPING_VALUE_ERROR(60000, "error","映射错误"),

	//后端错误
	TEST(60000,"error","后端错误");
	
	private final Integer code;

	private final String message ;

	private final String errorMsg ;

	public Integer code() {
		return this.code;
	}

	public String message() {
		return this.message;
	}

	public String errorMsg() {
		return this.errorMsg;
	}

	ResultCode(Integer code, String message, String errorMsg) {
		this.code = code;
		this.message = message;
		this.errorMsg = errorMsg;
	}


	public static String getErrorMsg(String name) {
		for (ResultCode item : ResultCode.values()) {
			if (item.name().equals(name)) {
				return item.message;
			}
		}
		return name;
	}

	public static String getMessage(String name) {
		for (ResultCode item : ResultCode.values()) {
			if (item.name().equals(name)) {
				return item.message;
			}
		}
		return name;
	}
	
	public static Integer getCode(String name) {
		for (ResultCode item : ResultCode.values()) {
			if (item.name().equals(name)) {
				return item.code;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return this.name();
	}
}
