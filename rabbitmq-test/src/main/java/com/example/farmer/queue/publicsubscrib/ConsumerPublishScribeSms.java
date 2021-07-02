package com.example.farmer.queue.publicsubscrib;

import com.example.farmer.constant.RabbitConstantCode;
import com.example.farmer.util.RabbitMqUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import lombok.SneakyThrows;

/**
 * @description: 发布订阅模式，短信消费者
 * @author: DaiXG
 * @createTime: 2021-06-26 13:45:00 周六
 */
public class ConsumerPublishScribeSms {
    @SneakyThrows
    public static void main(String[] args) {
        Connection connection;
        Channel channel;
        //创建链接
        connection = RabbitMqUtil.getConnection();

        //创建通道
        channel = connection.createChannel();

        //声明交换机
        channel.exchangeDeclare(RabbitConstantCode.EXCHANGE_FANOUT,
                BuiltinExchangeType.FANOUT);

        //声明队列(发送短信)
        channel.queueDeclare(RabbitConstantCode.FANOUT_SMS_QUEUE,
                true,
                false,
                false,
                null);

        //将交换机和队列(短信)绑定起来
        channel.queueBind(RabbitConstantCode.FANOUT_SMS_QUEUE,
                RabbitConstantCode.EXCHANGE_FANOUT,
                "");

        //消费者
        Consumer consumer = RabbitMqUtil.getConsumer(channel);

        /*
         * function 监听队列
         *
         * @param args 队列名称
         * @param args 是否自动回复，设置为true，表示收到了消息自动向MQ回复收到了，MQ会删除这个消息；
         * @param args 消费消息的方法，消费者接收到消息后，会调用这个方法
         */
        channel.basicConsume(RabbitConstantCode.FANOUT_SMS_QUEUE,
                true,
                consumer);
    }
}
