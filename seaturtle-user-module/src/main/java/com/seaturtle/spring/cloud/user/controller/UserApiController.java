package com.seaturtle.spring.cloud.user.controller;

import com.seaturtle.spring.cloud.user.model.Result;
import com.seaturtle.spring.cloud.user.model.dto.UserDto;
import com.seaturtle.spring.cloud.user.model.po.UserPo;
import com.seaturtle.spring.cloud.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * author Theft
 * date 2018/9/24
 */
@RestController
@RequestMapping("/module/user")
public class UserApiController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    @ResponseBody
    public Result<UserDto> register(@RequestBody UserPo userPo) {
        UserDto userDto = userService.register(userPo);
        return new Result<UserDto>().returnSuccess(userDto);
    }

}
