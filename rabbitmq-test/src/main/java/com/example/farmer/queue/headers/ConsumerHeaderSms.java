package com.example.farmer.queue.headers;

import com.example.farmer.constant.RabbitConstantCode;
import com.example.farmer.util.RabbitMqUtil;
import com.google.common.collect.Maps;
import com.rabbitmq.client.*;
import lombok.SneakyThrows;
import java.util.Map;

/**
 * @description: Header模式，短信消费者
 * @author: DaiXG
 * @createTime: 2021-06-27 17:16:00 周日
 */
public class ConsumerHeaderSms {

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

        Map<String, Object> headersSms = Maps.newHashMapWithExpectedSize(1);
        headersSms.put("inform_type","sms");

        channel.queueBind(
                RabbitConstantCode.HEADER_SMS_QUEUE,
                RabbitConstantCode.EXCHANGE_HEADER,
                "",
                headersSms
        );

        Consumer consumer = RabbitMqUtil.getConsumer(channel);
        channel.basicConsume(
                RabbitConstantCode.HEADER_SMS_QUEUE,
                true,
                consumer
        );
    }
}
