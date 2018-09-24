package com.seaturtle.spring.cloud.user.service;

import com.seaturtle.spring.cloud.user.model.dto.UserDto;
import com.seaturtle.spring.cloud.user.model.po.UserPo;

import java.util.List;

/**
 * author Theft
 * date 2018/9/23
 */
public interface UserService {

    UserDto register(UserPo userPo);

    UserDto findUserByUserId(Long userId);

    List<UserDto> findAllUser();
}
