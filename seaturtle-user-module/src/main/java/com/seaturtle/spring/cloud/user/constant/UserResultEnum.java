package com.seaturtle.spring.cloud.user.constant;

public enum UserResultEnum {

    SUCCESS(0, "请求成功"),
    USER_SERVER_ERROR(1, "用户模块系统异常");

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
