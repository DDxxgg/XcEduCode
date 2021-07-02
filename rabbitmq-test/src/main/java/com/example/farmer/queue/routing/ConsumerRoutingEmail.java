package com.example.farmer.queue.routing;

import com.example.farmer.constant.RabbitConstantCode;
import com.example.farmer.util.RabbitMqUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import lombok.SneakyThrows;

/**
 * @description: 路由模式消费者(邮件消息)测试
 * @author: DaiXG
 * @createTime: 2021-06-27 00:18:00 周日
 */
public class ConsumerRoutingEmail {
    @SneakyThrows
    public static void main(String[] args) {
        //创建一个与MQ的链接
        Connection connection = RabbitMqUtil.getConnection();

        //创建与交换机的通道，每一个通道就是一次会话
        Channel channel = connection.createChannel();

        /*
         * function 声明交换机
         *
         * @param args 交换机名称
         * @param args 交换机类型：指定DIRECT
         */
        channel.exchangeDeclare(RabbitConstantCode.EXCHANGE_DIRECT,
                BuiltinExchangeType.DIRECT);

        /*
         * function 声明队列(发送邮件)
         *
         * @param args 队列名称
         * @param args 是否持久化
         * @param args 是否独占此队列
         * @param args 队列不用是否删除
         * @param args 参数
         */
        channel.queueDeclare(RabbitConstantCode.DIRECT_EMAIL_QUEUE,
                true,
                false,
                false,
                null);

        /*
         * function 将交换机和队列(邮件)绑定起来
         *
         * @param args 队列名称
         * @param args 交换机名称
         * @param args 路由Key
         */
        channel.queueBind(RabbitConstantCode.DIRECT_EMAIL_QUEUE,
                RabbitConstantCode.EXCHANGE_DIRECT,
                RabbitConstantCode.DIRECT_EMAIL_ROUTING_KEY);

        //定义消费方法
        Consumer consumer = RabbitMqUtil.getConsumer(channel);

        /*
         * function 监听队列
         *
         * @param args 队列名称
         * @param args 是否自动回复，设置为true，表示收到了消息自动向MQ回复收到了，MQ会删除这个消息；
         * @param args 消费消息的方法，消费者接收到消息后，会调用这个方法
         */
        channel.basicConsume(RabbitConstantCode.DIRECT_EMAIL_QUEUE,
                true,
                consumer);
    }
}
