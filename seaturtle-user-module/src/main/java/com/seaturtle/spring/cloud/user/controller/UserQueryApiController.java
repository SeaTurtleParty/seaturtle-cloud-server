package com.seaturtle.spring.cloud.user.controller;

import java.util.List;

import com.seaturtle.spring.cloud.user.model.dto.UserDto;
import com.seaturtle.spring.cloud.user.service.UserService;
import com.seaturtle.spring.cloud.util.model.ResultUtil;
import com.seaturtle.spring.cloud.util.model.Result;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户查询模块控制器
 * @author Theft
 * date 2018/9/24
 */
@RestController
@RequestMapping("/module/user/query")
@Slf4j
public class UserQueryApiController {

    @Autowired
    private UserService userService;

    @GetMapping("/findByUserId")
    @ResponseBody
    public Result<UserDto> findByUserId(@RequestParam("userId") Long userId) {
    	return Try.of(() -> ResultUtil.buildSuccess(userService.findUserByUserId(userId)))
				.recover(ResultUtil.coverException())
				.get();
    }

	@GetMapping("/findAllUser")
	@ResponseBody
    public Result<List<UserDto>> findAllUser() {
		return Try.of(() -> ResultUtil.buildSuccess(userService.findAllUser()))
				.recover(ResultUtil.coverException())
				.get();
	}

}
