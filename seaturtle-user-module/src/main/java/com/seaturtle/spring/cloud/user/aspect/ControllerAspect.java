package com.seaturtle.spring.cloud.user.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.druid.support.json.JSONUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 控制器切面
 * @author Fei.Chu1
 * date 2018/9/30
 */
@Aspect
@Component
@Slf4j(topic = "REQUEST")
public class ControllerAspect {

	@Pointcut("execution(* com.seaturtle.spring.cloud.user.controller.*.*(..))")
	public void controllerPointcut() {}

	/**
	 * 方法前置切面
	 * @param joinPoint 切点信息
	 */
	@Before("controllerPointcut()")
	public void before(JoinPoint joinPoint) {
		RequestBody requestBody = getRequestBody(joinPoint);
		log.info("user-module, remoteAddr:{}, requestUrl:{}, method:{}, params:{}",
				requestBody.getRemoteAddr(), requestBody.getRequestUrl(),
				requestBody.getMethodName(), requestBody.getParams());
	}

	/**
	 * 方法异常抛出切面
	 * @param joinPoint 切点信息
	 * @param ex 抛出的异常{@link Exception}
	 */
	@AfterThrowing(pointcut = "controllerPointcut()", throwing = "ex")
	public void afterThrowing(JoinPoint joinPoint, Exception ex) {
		RequestBody requestBody = getRequestBody(joinPoint);
		log.info("user-module, remoteAddr:{}, requestUrl:{}, method:{}, params:{}, e: {}",
				requestBody.getRemoteAddr(), requestBody.getRequestUrl(),
				requestBody.getMethodName(), requestBody.getParams(), ex.toString());
	}

	private HttpServletRequest getHttpServletRequest() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		return sra == null ? null : sra.getRequest();
	}

	private RequestBody getRequestBody(JoinPoint joinPoint) {
		RequestBody requestBody = new RequestBody();

		HttpServletRequest request = getHttpServletRequest();

		String params = "";
		String methodName = joinPoint.getSignature().getName();
		String remoteAddr = "";
		String requestUrl = "";
		if (request != null) {
			// 获取请求方式
			String method = request.getMethod();
			if ("get".equalsIgnoreCase(method)) {
				params = request.getQueryString();
			} else if ("post".equalsIgnoreCase(method)) {
				params = JSONUtils.toJSONString(request.getParameterMap());
			}
			remoteAddr = request.getRemoteAddr();
			requestUrl = request.getRequestURL().toString();
		} else {
			params = Arrays.toString(joinPoint.getArgs());
		}

		requestBody.setParams(params);
		requestBody.setMethodName(methodName);
		requestBody.setRemoteAddr(remoteAddr);
		requestBody.setRequestUrl(requestUrl);
		return requestBody;
	}

	@Data
	private class RequestBody {
		String params;
		String methodName;
		String remoteAddr;
		String requestUrl;
	}

}
