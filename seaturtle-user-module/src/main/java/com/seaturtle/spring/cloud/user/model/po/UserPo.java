package com.seaturtle.spring.cloud.user.model.po;

import lombok.Data;

/**
 * 用户持久化对象模型
 * author Theft
 * date 2018/9/22
 */
@Data
public class UserPo {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户密码
     */
    private String password;

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

}
