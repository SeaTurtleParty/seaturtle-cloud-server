package com.seaturtle.spring.cloud.user.model;

import com.seaturtle.spring.cloud.user.constant.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author Theft
 * date 2018/9/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    private int code = 0;

    private String message = "";

    private T data = null;

    public boolean success() {
        return this.code == ResultEnum.SUCCESS.getCode();
    }

    public Result<T> returnSuccess(T data) {
        return new Result<>(0, "", data);
    }
}
