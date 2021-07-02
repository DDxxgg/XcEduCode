package com.example.farmer.queue.headers;

import com.example.farmer.constant.RabbitConstantCode;
import com.example.farmer.util.RabbitMqUtil;
import com.google.common.collect.Maps;
import com.rabbitmq.client.*;
import lombok.SneakyThrows;

import java.util.Map;

/**
 * @description: Header模式，邮件消费者
 * @author: DaiXG
 * @createTime: 2021-06-27 17:16:00 周日
 */
public class ConsumerHeaderEmail {

    @SneakyThrows
    public static void main(String[] args) {
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(
                RabbitConstantCode.EXCHANGE_HEADER,
                BuiltinExchangeType.HEADERS
        );

        channel.queueDeclare(
                RabbitConstantCode.HEADER_EMAIL_QUEUE,
                true,
                false,
                false,
                null
        );

        Map<String, Object> headersEmail = Maps.newHashMapWithExpectedSize(1);
        headersEmail.put("inform_type","email");
        channel.queueBind(
                RabbitConstantCode.HEADER_EMAIL_QUEUE,
                RabbitConstantCode.EXCHANGE_HEADER,
                "",
                headersEmail
        );

        AMQP.BasicProperties.Builder properties = new AMQP.BasicProperties.Builder();
        properties.headers(headersEmail);

        Consumer consumer = RabbitMqUtil.getConsumer(channel);

        channel.basicConsume(
                RabbitConstantCode.HEADER_EMAIL_QUEUE,
                true,
                consumer
        );
    }



}
