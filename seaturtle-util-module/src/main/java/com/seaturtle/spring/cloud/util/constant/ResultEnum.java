package com.seaturtle.spring.cloud.util.constant;

/**
 * 结果枚举类
 * @author Fei.Chu1
 * date 2018/09/25
 */
public enum ResultEnum {

	/**
	 * 请求成功
	 */
	SUCCESS(0, "请求成功"),
	/**
	 * 系统异常
	 */
    SERVER_ERROR(1, "用户模块系统异常");

    private int code;
    private String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {return this.message; }
}
