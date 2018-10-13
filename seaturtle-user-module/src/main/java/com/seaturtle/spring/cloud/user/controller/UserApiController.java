package com.seaturtle.spring.cloud.user.controller;

import com.seaturtle.spring.cloud.util.model.ResultUtil;
import com.seaturtle.spring.cloud.util.model.Result;
import com.seaturtle.spring.cloud.user.model.dto.UserDto;
import com.seaturtle.spring.cloud.user.model.po.UserPo;
import com.seaturtle.spring.cloud.user.service.UserService;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户模块控制器
 * @author Theft
 * date 2018/9/24
 */
@RestController
@RequestMapping("/module/user")
@Slf4j
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ResponseBody
    public Result<UserDto> register(@RequestParam("name") String name, @RequestParam("password") String password,
                                    @RequestParam("gender") Integer gender, @RequestParam("mail") String mail,
                                    @RequestParam("phone") String phone) {
        UserPo userPo = new UserPo();
        userPo.setUserName(name);
        userPo.setPassword(password);
        userPo.setGender(gender);
        userPo.setUserMail(mail);
        userPo.setUserPhone(phone);
        return Try.of(() -> ResultUtil.buildSuccess(userService.register(userPo)))
				.recover(ResultUtil.coverException())
				.get();
    }

}
