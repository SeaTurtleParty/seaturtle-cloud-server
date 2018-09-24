package com.seaturtle.spring.cloud.user.exception;

import com.seaturtle.spring.cloud.user.constant.UserResultEnum;
import com.seaturtle.spring.cloud.util.exception.SeaturtleException;

/**
 * author Theft
 * date 2018/9/24
 */
public class UserModuleException extends SeaturtleException {

    public UserModuleException(UserResultEnum result) {
        super();
    }

}
