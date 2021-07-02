package com.example.farmer.producer;

import com.example.farmer.config.RabbitMqConfig;
import com.example.farmer.constant.RabbitConstantCode;
import com.example.farmer.controller.ReceiveHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description: SpringBoot测试类来模拟RabbitMQ消息生产者
 * @author: DaiXG
 * @createTime: 2021-06-27 18:45:00 周日
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProducerTopicBySpringBoot {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendSmsMsgByTopic() {
        int num = 10;
        for (int i = 1; i <= num; i++) {
            String msg = "短信消息-" + i;
            rabbitTemplate.convertAndSend(
                    RabbitConstantCode.EXCHANGE_TOPIC,
                    RabbitConstantCode.TOPIC_ROUTING_KEY_SEND_SMS,
                    msg);
            System.out.println("主题模式：" + msg);
        }
    }

    @Test
    public void testSendEmailMsgByTopic() {
        int num = 10;
        for (int i = 1; i <= num; i++) {
            String msg = "邮件消息-" + i;
            rabbitTemplate.convertAndSend(
                    RabbitConstantCode.EXCHANGE_TOPIC,
                    RabbitConstantCode.TOPIC_ROUTING_KEY_SEND_EMAIL,
                    msg);
            System.out.println("主题模式：" + msg);
        }
    }
}
