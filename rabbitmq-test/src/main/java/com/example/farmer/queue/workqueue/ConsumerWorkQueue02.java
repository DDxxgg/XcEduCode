package com.example.farmer.queue.workqueue;

import com.example.farmer.constant.RabbitConstantCode;
import com.example.farmer.util.RabbitMqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import lombok.SneakyThrows;


/**
 * @description: 消费者02
 * @author: DaiXG
 * @createTime: 2021-06-12 22:25:00 周六
 */
public class ConsumerWorkQueue02 {
    @SneakyThrows
    public static void main(String[] args) {
        Connection connection = RabbitMqUtil.getConnection();
        //创建与交换机的通道，每一个通道就是一次会话
        Channel channel = connection.createChannel();

        //4、用通道声明交换机(这里使用MQ默认的交换机，不用自定义交换机)

        /*
         * function 5、用通道声明队列
         *
         * @author DaiXG
         * @createTime 2021/6/12 21:54
         * @param args 生命队列名称为：HelloWorld
         * @param args 是否持久化
         * @param args 队列是否独占此队列
         * @param args 队列不再使用时是否自动删除此队列
         * @param args 队列的参数
         */
        channel.queueDeclare(RabbitConstantCode.WORK_QUEUE,
                true,
                false,
                false,
                null);

        //6、管道对应的消费者
        Consumer consumer = RabbitMqUtil.getConsumer(channel);

        /*
         * function (监听Broker里名称为QUEUE[HelloWorld]的队列)
         *          消费者通过监听队列->管道接收到消息，并调用defaultConsumer来消费方法
         *
         * @author DaiXG
         * @createTime 2021/6/12 22:51
         * @param QUEUE 队列名称
         * @param true 表示消费者收到消息后是否自动向MQ发送消息，true表示自动向MQ发送消息，MQ收到消息后会删除这条消息
         *             设置为false，需要手动删除
         * @param defaultConsumer 消费消息的方法，消费者收到消息后调用这个方法来
         */
        channel.basicConsume(RabbitConstantCode.WORK_QUEUE, true, consumer);
    }
}
