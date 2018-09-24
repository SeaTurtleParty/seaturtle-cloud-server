package com.seaturtle.spring.cloud.user.mapper.sqlProvider;

import com.seaturtle.spring.cloud.user.model.po.UserPo;
import org.apache.ibatis.jdbc.SQL;

/**
 * 用户表动态sql
 * author Theft
 * date 2018/9/24
 */
public class UserSqlProvider {

    public String insertUser(UserPo userPo) {
        SQL sql = new SQL();
        sql.INSERT_INTO("user");
        if (userPo.getUserId() != null) {
            sql.VALUES("user_id", "#{userId}");
        }
        if (userPo.getUserName() != null) {
            sql.VALUES("name", "#{userName}");
        }
        if (userPo.getPassword() != null) {
            sql.VALUES("password", "#{password}");
        }
        if (userPo.getUserMail() != null) {
            sql.VALUES("mail", "#{userMail}");
        }
        if (userPo.getUserPhone() != null) {
            sql.VALUES("phone", "#{userPhone}");
        }
        sql.VALUES("register_time", "now()");
        sql.VALUES("last_login_time", "now()");
        return sql.toString();
    }
}
