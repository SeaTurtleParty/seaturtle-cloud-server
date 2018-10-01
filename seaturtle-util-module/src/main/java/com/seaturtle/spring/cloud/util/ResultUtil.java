package com.seaturtle.spring.cloud.util;

import static com.google.common.base.Predicates.instanceOf;
import static io.vavr.API.*;

import java.util.function.Function;

import com.seaturtle.spring.cloud.util.constant.ResultEnum;
import com.seaturtle.spring.cloud.util.exception.SeaturtleException;
import com.seaturtle.spring.cloud.util.model.Result;

/**
 * 请求结果封装工具类
 * @author Fei.Chu1
 * @date 2018/10/1
 */
public class ResultUtil {

	/**
	 * 构造请求成功结果
	 * @param data 请求结果数据
	 * @param <T> 泛型
	 * @return {@link Result<T>}
	 */
	public static <T> Result<T> buildSuccess(T data) {
		return new Result<T>().returnSuccess(data);
	}

	/**
	 * 构造请求失败结果
	 * @param code 失败码
	 * @param message 失败消息
	 * @param <T> 泛型
	 * @return {@link Result<T>}
	 */
	private static <T> Result<T> buildFail(int code, String message) {
		return new Result<T>().returnFail(code, message);
	}

	/**
	 * 构造请求失败结果
	 * @param resultEnum 结果返回类型
	 * @param <T> 泛型
	 * @return {@link Result<T>}
	 */
	private static <T> Result<T> buildFail(ResultEnum resultEnum) {
		return new Result<T>().returnFail(resultEnum.getCode(), resultEnum.getMessage());
	}

	/**
	 * 构造请求失败结果
	 * @param e 结果返回异常
	 * @param <T> 泛型
	 * @return {@link Result<T>}
	 */
	private static <T> Result<T> buildError(SeaturtleException e) {
		return buildFail(e.getCode(), e.getMessage());
	}

	/**
	 * 自定义函数异常覆盖方法
	 * @param <T> 泛型
	 * @return {@link Function<Throwable, Result<T>>}
	 */
	public static <T> Function<Throwable, Result<T>> coverException() {
		return e -> Match(e).of(
				Case($(instanceOf(SeaturtleException.class)), buildError((SeaturtleException) e)),
				Case($(), buildFail(ResultEnum.SERVER_ERROR))
		);
	}

}
