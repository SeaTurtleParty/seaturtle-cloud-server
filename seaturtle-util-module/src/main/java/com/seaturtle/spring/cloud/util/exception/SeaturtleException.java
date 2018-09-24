package com.seaturtle.spring.cloud.util.exception;

/**
 * author Theft
 * date 2018/9/24
 */
public class SeaturtleException extends RuntimeException {

    private int code;

    private String msg;

    private Throwable ex;

    public SeaturtleException() {};

    public SeaturtleException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
