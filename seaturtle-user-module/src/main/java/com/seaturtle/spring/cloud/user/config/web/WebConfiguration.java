package com.seaturtle.spring.cloud.user.config.web;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * mvc配置
 * @author Theft
 * date 2018/10/28
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * 设置跨域
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // TODO
    }

    /**
     * 额外扩展消息格式转化器
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter messageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        /*
         * fastjson序列化容器
         * 举几个例子，WriteDateUseDateFormat「全局修改日期格式,默认为false,JSON.DEFFAULT_DATE_FORMAT = “yyyy-MM-dd”」
         * 默认将Date序列化为13位时间戳，可以通过@JSONField对字段自定义格式化
         * WriteMapNullValue 「是否输出值为null的字段,默认为false」
         */
        fastJsonConfig.setSerializerFeatures(
                // 清爽格式化
                SerializerFeature.PrettyFormat
        );
        messageConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(messageConverter);
    }
}
