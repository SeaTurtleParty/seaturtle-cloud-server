package com.seaturtle.spring.cloud.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * 用户模块启动类
 * @author chufei
 * 2018年7月11日
 */
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = "com.seaturtle.spring.cloud.user")
public class UserServerApplication {

	@Value("${spring.application.name}")
	private static String name;

	/**
	 * 项目启动监听器加载顺序
	 * ApplicationStartingEvent->ApplicationEnvironmentPreparedEvent
	 * ->ApplicationPreparedEvent->ApplicationStartedEvent
	 * ->ApplicationReadyEvent
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(UserServerApplication.class, args);
	}
}
