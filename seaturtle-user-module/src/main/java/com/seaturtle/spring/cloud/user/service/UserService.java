package com.seaturtle.spring.cloud.user.service;

import com.seaturtle.spring.cloud.user.model.dto.UserDto;
import com.seaturtle.spring.cloud.user.model.po.UserPo;

import java.util.List;

/**
 * 用户模块业务
 * @author Theft
 * date 2018/9/23
 */
public interface UserService {

    /**
     * 用户注册
     * @param userPo 注册参数
     * @return 返回用户对象{@link UserDto}
     */
    UserDto register(UserPo userPo);

    /**
     * 根据用户id查询用户信息
     * @param userId 用户id
     * @return 返回用户对象{@link UserDto}
     */
    UserDto findUserByUserId(Long userId);

    /**
     * 返回所有用户信息
     * @return 返回用户对象集合List#{@link UserDto}
     */
    List<UserDto> findAllUser();
}
