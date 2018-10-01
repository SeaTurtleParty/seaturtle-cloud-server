package com.seaturtle.spring.cloud.util.model;

import com.seaturtle.spring.cloud.util.constant.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 请求结果包装类
 * @author Theft
 * date 2018/9/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    private Integer code;

    private String message = "";

    private T data = null;

    public boolean success() {
        return this.code == ResultEnum.SUCCESS.getCode();
    }

    public Result<T> returnSuccess(T data) {
    	this.code = ResultEnum.SUCCESS.getCode();
    	this.data = data;
        return this;
    }

    public Result<T> returnFail(int code, String message) {
    	this.code = code;
    	this.message = message;
    	return this;
	}
}
