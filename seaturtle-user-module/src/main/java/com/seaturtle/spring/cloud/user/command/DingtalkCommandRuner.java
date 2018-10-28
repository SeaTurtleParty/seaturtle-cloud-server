package com.seaturtle.spring.cloud.user.command;

import com.dingtalk.chatbot.DingtalkChatbotClient;
import com.dingtalk.chatbot.SendResult;
import com.dingtalk.chatbot.message.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * 钉钉机器人启动器
 * @author Theft
 * date 2018/10/28
 */
@Component
@Order(1)
@Slf4j
public class DingtalkCommandRuner implements CommandLineRunner {

    private DingtalkChatbotClient client;

    @Value("${spring.application.name}")
    private String appName;

    @Value("${dingtalk.robot.url}")
    private String dingTalkUrl;

    @Value("${dingtalk.robot.notify}")
    private boolean dingTalkNotify;

    @PostConstruct
    public void init() {
        client = new DingtalkChatbotClient();
    }

    @Override
    public void run(String... args) throws Exception {
        if (dingTalkNotify) {
            TextMessage message = new TextMessage(appName + "发布, 时间: " + new Date());
            SendResult result = this.client.send(dingTalkUrl, message);
            if (result.isSuccess()) {
                log.info("dingtalk robot notify success!");
            } else {
                log.error("dingtalk rebot notify fail, error message: {}", result.getErrorMsg());
            }
        }
    }
}
