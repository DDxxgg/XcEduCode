package com.example.farmer.queue.topics;

import com.example.farmer.constant.RabbitConstantCode;
import com.example.farmer.util.RabbitMqUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import lombok.SneakyThrows;

/**
 * @description: 主题模式，短信消费者
 * @author: DaiXG
 * @createTime: 2021-06-27 11:10:00 周日
 */
public class ConsumerTopicsSms {
    @SneakyThrows
    public static void main(String[] args) {
        //创建一个与MQ的连接
        Connection connection = RabbitMqUtil.getConnection();

        //创建一个与交换机的通道，每一个通道都是一次会话
        //创建与交换机的通道，每一个通道就是一次会话
        Channel channel = connection.createChannel();

        /*
         * function 声明队列(发送短信)
         *
         * @param args 队列名称
         * @param args 是否持久化
         * @param args 是否独占此队列
         * @param args 队列不用是否删除
         * @param args 参数
         */
        channel.queueDeclare(RabbitConstantCode.TOPIC_SMS_QUEUE,
                true,
                false,
                false,
                null);

        /*
         * function 绑定队列和交换机
         *
         * @param args 队列名
         * @param args 交换机
         * @param args 路由
         */
        channel.queueBind(RabbitConstantCode.TOPIC_SMS_QUEUE,
                RabbitConstantCode.EXCHANGE_TOPIC,
                RabbitConstantCode.TOPIC_SMS_ROUTING_KEY);

        //消费者
        Consumer consumer = RabbitMqUtil.getConsumer(channel);

        /*
         * function 监听队列
         *
         * @param args 队列名称
         * @param args 是否自动回复，设置为true，表示收到了消息自动向MQ回复收到了，MQ会删除这个消息；
         * @param args 消费消息的方法，消费者接收到消息后，会调用这个方法
         */
        channel.basicConsume( RabbitConstantCode.TOPIC_SMS_QUEUE,
                true,
                consumer);
    }
}
