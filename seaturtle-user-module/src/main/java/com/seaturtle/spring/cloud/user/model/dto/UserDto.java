package com.seaturtle.spring.cloud.user.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * 用户信息模型
 * @author Theft
 * date 2018/9/22
 */
@Data
public class UserDto {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户邮箱
     */
    private String userMail;

    /**
     * 用户手机号
     */
    private String userPhone;

    /**
     * 用户性别
     */
    private Integer gender;

    /**
     * 用户注册时间
     */
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date registerTime;

    /**
     * 用户最后一次登录时间
     */
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date lastLoginTime;
}
