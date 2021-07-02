package com.example.farmer.queue.publicsubscrib;

import com.example.farmer.constant.RabbitConstantCode;
import com.example.farmer.util.RabbitMqUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import lombok.SneakyThrows;

/**
 * @description: 发布订阅模式，邮件消费者
 * @author: DaiXG
 * @createTime: 2021-06-26 13:45:00 周六
 */
public class ConsumerPublishScribeEmail {
    @SneakyThrows
    public static void main(String[] args) {
        Connection connection;
        Channel channel;
        //创建一个与MQ的链接
        connection = RabbitMqUtil.getConnection();

        //创建与交换机的通道，每一个通道就是一次会话
        channel = connection.createChannel();

        /*
         * function 声明交换机
         *
         * @param args 交换机名称
         * @param args 交换机类型：fanout,topic,direct,headers
         */
        channel.exchangeDeclare(RabbitConstantCode.EXCHANGE_FANOUT,
                BuiltinExchangeType.FANOUT);

        /*
         * function 声明队列(发送邮件) 如果队列不存在，就创建新队列
         *
         * @param args 队列名称
         * @param args 是否持久化
         * @param args 是否独占此队列
         * @param args 队列不用是否删除
         * @param args 参数
         */
        channel.queueDeclare(RabbitConstantCode.FANOUT_EMAIL_QUEUE,
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
        channel.queueBind(RabbitConstantCode.FANOUT_EMAIL_QUEUE,
                RabbitConstantCode.EXCHANGE_FANOUT,
                "");

        //定义消费方法
        Consumer consumer = RabbitMqUtil.getConsumer(channel);

        /*
         * function 监听队列
         *
         * @param args 队列名称
         * @param args 是否自动回复，设置为true，表示收到了消息自动向MQ回复收到了，MQ会删除这个消息；
         * @param args 消费消息的方法，消费者接收到消息后，会调用这个方法
         */
        channel.basicConsume(RabbitConstantCode.FANOUT_EMAIL_QUEUE,
                true,
                consumer);
    }
}
