package com.seaturtle.spring.cloud.util.generator;

import java.util.UUID;

/**
 * ID生成器
 * @author Fei.Chu1
 * @date 2018/9/25
 */
public class IdGenerator {

	/**
	 * 用UUID随机生成用户Id
	 * @return {@link Long}型用户id
	 */
	public static Long generUserId() {
		UUID uuid = UUID.randomUUID();
		return (long) Math.abs(uuid.hashCode());
	}

}
