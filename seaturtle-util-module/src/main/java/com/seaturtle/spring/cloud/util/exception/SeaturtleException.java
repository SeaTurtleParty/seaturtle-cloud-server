package com.seaturtle.spring.cloud.util.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 异常基类
 * @author Theft
 * date 2018/9/24
 */
@Getter
@Setter
@NoArgsConstructor
public class SeaturtleException extends RuntimeException {

    private int code;

    private String msg;

    private Throwable ex;

    public SeaturtleException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
