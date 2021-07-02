package com.example.farmer.queue.routing;

import com.example.farmer.constant.RabbitConstantCode;
import com.example.farmer.util.RabbitMqUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import lombok.SneakyThrows;

/**
 * @description: 路由模式消费者(短信消息)测试
 * @author: DaiXG
 * @createTime: 2021-06-27 00:09:00 周日
 */
public class ConsumerRoutingSms {
    @SneakyThrows
    public static void main(String[] args) {
        Connection connection;
        Channel channel;
        //创建链接
        connection = RabbitMqUtil.getConnection();

        //创建与交换机的通道，每一个通道就是一次会话
        channel = connection.createChannel();

        //声明交换机
        channel.exchangeDeclare(RabbitConstantCode.EXCHANGE_DIRECT,
                BuiltinExchangeType.DIRECT);

        //声明队列(发送短信)
        channel.queueDeclare(RabbitConstantCode.DIRECT_SMS_QUEUE,
                true,
                false,
                false,
                null);

        //将交换机和队列(短信)绑定起来
        channel.queueBind(RabbitConstantCode.DIRECT_SMS_QUEUE,
                RabbitConstantCode.EXCHANGE_DIRECT,
                RabbitConstantCode.DIRECT_SMS_ROUTING_KEY);

        //消费者
        Consumer consumer = RabbitMqUtil.getConsumer(channel);

        //监听队列
        channel.basicConsume(RabbitConstantCode.DIRECT_SMS_QUEUE,
                true,
                consumer);
    }
}
