package com.seaturtle.spring.cloud.user.constant;

public enum ResultEnum {

    SUCCESS(0, "请求成功");

    private int code;
    private String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }
}
