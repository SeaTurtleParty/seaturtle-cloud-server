package com.seaturtle.spring.cloud.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.seaturtle.spring.cloud.user.constant.UserResultEnum;
import com.seaturtle.spring.cloud.user.exception.UserModuleException;
import com.seaturtle.spring.cloud.user.mapper.UserMapper;
import com.seaturtle.spring.cloud.user.model.dto.UserDto;
import com.seaturtle.spring.cloud.user.model.po.UserPo;
import com.seaturtle.spring.cloud.user.service.UserService;
import com.seaturtle.spring.cloud.util.encrypt.EncryptUtil;
import com.seaturtle.spring.cloud.util.generator.IdGenerator;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 用户模块业务实现
 * @author Theft
 * date 2018/9/24
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto register(UserPo userPo) {
    	// 校验手机号是否已存在
		Integer count = userMapper.getUserCountByPhone(userPo.getUserPhone());
		if (count != null) throw new UserModuleException(UserResultEnum.FAIL_PHONE_EXIST);

		long userId = IdGenerator.generUserId();
		userPo.setUserId(userId);
		userPo.setPassword(new EncryptUtil(userPo.getPassword(), null, EncryptUtil.ENCRYPT_MD5).encodeBySalt());
		// 注册用户
		Integer row = Try.of(() -> userMapper.register(userPo))
				.onFailure(e -> log.error("UserService#register is error, userPo:{}, e:{}", JSON.toJSONString(userPo), e.toString()))
				.getOrElseThrow(() -> new UserModuleException(UserResultEnum.USER_SERVER_ERROR));
		if (row < 1) throw new UserModuleException(UserResultEnum.USER_SERVER_ERROR);
		return this.findUserByUserId(userId);
    }

    @Override
    public UserDto findUserByUserId(Long userId) {
		UserDto userDto = Try.of(() -> userMapper.getUserByUserId(userId))
				.onFailure(e -> log.error("UserService#findUserByUserId is error, userId:{}, e:{}", userId, e.toString()))
				.getOrElseThrow(() -> new UserModuleException(UserResultEnum.USER_SERVER_ERROR));
		Optional.ofNullable(userDto).orElseThrow(() -> new UserModuleException(UserResultEnum.FAIL_USER_NOT_EXIST));

		return userDto;
	}

    @Override
    public List<UserDto> findAllUser() {
    	return Try.of(() -> userMapper.getAllUsers())
				.onFailure(e -> log.error("UserService#findAllUser is error, e:{}", e.toString()))
				.getOrElseThrow(() -> new UserModuleException(UserResultEnum.USER_SERVER_ERROR));
    }
}
