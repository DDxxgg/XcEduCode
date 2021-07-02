package com.example.farmer.queue.headers;

import com.example.farmer.constant.RabbitConstantCode;
import com.example.farmer.util.RabbitMqUtil;
import com.google.common.collect.Maps;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @description: Headers模式，消息生产者
 * @author: DaiXG
 * @createTime: 2021-06-27 16:58:00 周日
 */
public class ProducerHeader {

    @SneakyThrows
    public static void main(String[] args) {
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(
                RabbitConstantCode.EXCHANGE_HEADER,
                BuiltinExchangeType.HEADERS
        );

        channel.queueDeclare(
                RabbitConstantCode.HEADER_SMS_QUEUE,
                true,
                false,
                false,
                null
        );
        channel.queueDeclare(
                RabbitConstantCode.HEADER_EMAIL_QUEUE,
                true,
                false,
                false,
                null
        );


        Map<String, Object> headersEmail = Maps.newHashMapWithExpectedSize(1);
        Map<String, Object> headersSms = Maps.newHashMapWithExpectedSize(1);
        headersEmail.put("inform_type", "email");
        headersSms.put("inform_type", "sms");

        channel.queueBind(
                RabbitConstantCode.HEADER_SMS_QUEUE,
                RabbitConstantCode.EXCHANGE_HEADER,
                "",
                headersSms
        );
        channel.queueBind(
                RabbitConstantCode.HEADER_EMAIL_QUEUE,
                RabbitConstantCode.EXCHANGE_HEADER,
                "",
                headersEmail
        );

        AMQP.BasicProperties.Builder properties = new AMQP.BasicProperties.Builder();
        properties.headers(headersEmail);

        int num = 10;
        for (int i = 1; i <= num; i++) {
            String msg = "邮件消息-" + i;
            channel.basicPublish(
                    RabbitConstantCode.EXCHANGE_HEADER,
                    "",
                    properties.build(),
                    msg.getBytes(StandardCharsets.UTF_8)
            );
            System.out.println("Header发送邮件消息:" + msg);
        }

        AMQP.BasicProperties.Builder properties2 = new AMQP.BasicProperties.Builder();
        properties2.headers(headersSms);
        for (int i = 1; i <= num; i++) {
            String msg = "短信消息-" + i;
            channel.basicPublish(
                    RabbitConstantCode.EXCHANGE_HEADER,
                    "",
                    properties2.build(),
                    msg.getBytes(StandardCharsets.UTF_8)
            );
            System.out.println("Header发送短信消息:" + msg);
        }
    }
}
