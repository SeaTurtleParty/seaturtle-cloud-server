package com.seaturtle.spring.cloud.user.service.impl;

import com.seaturtle.spring.cloud.user.generator.UserIdGenerator;
import com.seaturtle.spring.cloud.user.mapper.UserMapper;
import com.seaturtle.spring.cloud.user.model.dto.UserDto;
import com.seaturtle.spring.cloud.user.model.po.UserPo;
import com.seaturtle.spring.cloud.user.service.UserService;
import com.seaturtle.spring.cloud.util.encrypt.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author Theft
 * date 2018/9/24
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto register(UserPo userPo) {
        userPo.setUserId(UserIdGenerator.generUserId());
        userPo.setPassword(new EncryptUtil(userPo.getPassword(), null, EncryptUtil.ENCRYPT_MD5).encodeBySalt());
        Long userId = userMapper.register(userPo);
        return this.findUserByUserId(userId);
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
