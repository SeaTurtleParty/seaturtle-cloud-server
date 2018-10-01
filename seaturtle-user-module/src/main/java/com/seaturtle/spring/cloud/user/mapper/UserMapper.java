package com.seaturtle.spring.cloud.user.mapper;

import java.util.Date;
import java.util.List;

import com.seaturtle.spring.cloud.user.mapper.sqlprovider.UserSqlProvider;
import com.seaturtle.spring.cloud.user.model.dto.UserDto;
import com.seaturtle.spring.cloud.user.model.po.UserPo;
import org.apache.ibatis.annotations.*;

/**
 * 用户相关数据操作
 * @author chufei
 * date 2017年4月10日
 */
@Mapper
public interface UserMapper {

	/**
	 * 用户注册
	 * 
	 * @param userPo 注册参数
	 */
	@InsertProvider(type = UserSqlProvider.class, method = "insertUser")
	Integer register(UserPo userPo);

	/**
	 * 根据用户id登录
	 * 
	 * @param userId 用户id
	 * @param password 密码
	 * @return 返回用户id
	 */
	@Select(value = "select user_id from user where user_id = #{userId} and password = #{password}")
	Long loginByUserId(Long userId, String password);

	/**
	 * 根据手机号登录
	 * 
	 * @param phone 手机号
	 * @param password 密码
	 * @return 返回用户id
	 */
	@Select(value = "select user_id from user where phone = #{phone} and password = #{password}")
	Long loginByPhone(String phone, String password);

	/**
	 * 判断昵称是否已存在
	 * 
	 * @param userId 用户id
	 * @return 返回1表示已存在，null表示不存在
	 */
	@Select(value = "select 1 from user where user_id = #{userId}")
	Integer getUserCountByUserId(Long userId);

	/**
	 * 判断手机号是否已存在
	 * 
	 * @param phone 手机号
	 * @return 返回1表示已存在，null表示不存在
	 */
	@Select(value = "select 1 from user where phone = #{phone}")
	Integer getUserCountByPhone(String phone);

	/**
	 * 根据用户id获取用户信息
	 * 
	 * @param userId 用户id
	 * @return 返回用户对象User
	 */
	@Select(value = "select * from user where user_id = #{userId}")
	@Results({
			@Result(column = "user_id", property = "userId"),
			@Result(column = "name", property = "userName"),
			@Result(column = "gender", property = "gender"),
			@Result(column = "mail", property = "userMail"),
			@Result(column = "phone", property = "userPhone"),
			@Result(column = "register_time", property = "registerTime")
	})
	UserDto getUserByUserId(Long userId);

	/**
	 * 获取所有用户信息
	 * 
	 * @return 返回List<{@link UserInfo}>
	 */
	@Select(value = "select * from user")
	@Results({
			@Result(column = "user_id", property = "userId"),
			@Result(column = "name", property = "userName"),
			@Result(column = "gender", property = "gender"),
			@Result(column = "mail", property = "userMail"),
			@Result(column = "phone", property = "userPhone"),
			@Result(column = "register_time", property = "registerTime", javaType = Date.class),
			@Result(column = "last_login_time", property = "lastLoginTime", javaType = Date.class)
	})
	List<UserDto> getAllUsers();

}
