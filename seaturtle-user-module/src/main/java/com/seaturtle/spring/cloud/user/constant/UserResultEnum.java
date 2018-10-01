package com.seaturtle.spring.cloud.user.constant;

/**
 * 用户模块结果枚举类
 * @author Fei.Chu1
 * date 2018/09/25
 */
public enum UserResultEnum {

	/**
	 * 用户模块系统异常
	 */
    USER_SERVER_ERROR(1001, "用户模块系统异常"),
	/**
	 * 手机号已存在
	 */
    FAIL_PHONE_EXIST(1002, "手机号已存在"),
	/**
	 * 该用户不存在
	 */
	FAIL_USER_NOT_EXIST(1003, "该用户不存在");

    private int code;
    private String message;

    UserResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {return this.message; }
}
