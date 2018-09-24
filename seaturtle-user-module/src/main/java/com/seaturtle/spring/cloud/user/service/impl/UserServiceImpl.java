package com.seaturtle.spring.cloud.user.service.impl;

import com.seaturtle.spring.cloud.user.constant.UserResultEnum;
import com.seaturtle.spring.cloud.user.generator.UserIdGenerator;
import com.seaturtle.spring.cloud.user.mapper.UserMapper;
import com.seaturtle.spring.cloud.user.model.dto.UserDto;
import com.seaturtle.spring.cloud.user.model.po.UserPo;
import com.seaturtle.spring.cloud.user.service.UserService;
import com.seaturtle.spring.cloud.util.exception.SeaturtleException;
import com.seaturtle.spring.cloud.util.encrypt.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户模块业务实现
 * author Theft
 * date 2018/9/24
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto register(UserPo userPo) {
        try {
            long userId = UserIdGenerator.generUserId();
            userPo.setUserId(userId);
            userPo.setPassword(new EncryptUtil(userPo.getPassword(), null, EncryptUtil.ENCRYPT_MD5).encodeBySalt());
            userMapper.register(userPo);
            return this.findUserByUserId(userId);
        } catch (Exception e) {
            throw new SeaturtleException(UserResultEnum.USER_SERVER_ERROR.getCode(), UserResultEnum.USER_SERVER_ERROR.getMessage());
        }
    }

    @Override
    public UserDto findUserByUserId(Long userId) {
        return userMapper.getUserByUserId(userId);
    }

    @Override
    public List<UserDto> findAllUser() {
        return userMapper.getAllUsers();
    }
}
