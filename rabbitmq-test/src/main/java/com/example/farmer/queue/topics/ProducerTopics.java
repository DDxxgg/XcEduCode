package com.example.farmer.queue.topics;

import com.example.farmer.constant.RabbitConstantCode;
import com.example.farmer.util.RabbitMqUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import lombok.SneakyThrows;
import org.springframework.util.ObjectUtils;

import java.nio.charset.StandardCharsets;

/**
 * @description: 主题模式，生产者测试
 * @author: DaiXG
 * @createTime: 2021-06-27 10:25:00 周日
 */
public class ProducerTopics {
    @SneakyThrows
    public static void main(String[] args) {
        //创建一个与MQ的连接
        Connection connection = RabbitMqUtil.getConnection();

        //创建一个与交换机的通道，每一个通道都是一次会话
        //创建与交换机的通道，每一个通道就是一次会话
        Channel channel = connection.createChannel();

        /*
         * function 定义交换机
         *
         * @param args 交换机名称
         * @param args 交换机类型，指定TOPIC类型
         */
        channel.exchangeDeclare(RabbitConstantCode.EXCHANGE_TOPIC,
                BuiltinExchangeType.TOPIC);

        /*
         * function 声明队列(发送短信的队列)
         *
         * @param args 指定队列的名称
         * @param args 是否持节话
         * @param args 是否独占此队列
         * @param args 队列不用是否删除
         * @param args 参数
         */
        channel.queueDeclare(RabbitConstantCode.TOPIC_SMS_QUEUE,
                true,
                false,
                false,
                null);
        //声明队列(发送邮件的队列)
        channel.queueDeclare(RabbitConstantCode.TOPIC_EMAIL_QUEUE,
                true,
                false,
                false,
                null);

        /*
         * function 绑定队列和交换机，并指定路由名称是队列名称
         *
         * @param args 队列名称
         * @param args 交换机名称
         * @param args 统配符路由
         */
        channel.queueBind(RabbitConstantCode.TOPIC_SMS_QUEUE,
                RabbitConstantCode.EXCHANGE_TOPIC,
                RabbitConstantCode.TOPIC_SMS_ROUTING_KEY);
        channel.queueBind(RabbitConstantCode.TOPIC_EMAIL_QUEUE,
                RabbitConstantCode.EXCHANGE_TOPIC,
                RabbitConstantCode.TOPIC_EMAIL_ROUTING_KEY);

        int num = 10;
        for (int i = 1; i <= num; i++) {
            String msg = "短信消息-" + i;
            channel.basicPublish(RabbitConstantCode.EXCHANGE_TOPIC,
                    RabbitConstantCode.TOPIC_ROUTING_KEY_SEND_SMS,
                    null,
                    msg.getBytes(StandardCharsets.UTF_8));
            System.out.println("主题模式：" + msg);
        }

        for (int i = 1; i <= num; i++) {
            String msg = "邮件消息-" + i;
            channel.basicPublish(RabbitConstantCode.EXCHANGE_TOPIC,
                    RabbitConstantCode.TOPIC_ROUTING_KEY_SEND_EMAIL,
                    null,
                    msg.getBytes(StandardCharsets.UTF_8));
            System.out.println("主题模式：" + msg);
        }

        for (int i = 1; i <= num; i++) {
            String msg = "短信/邮件消息-" + i;
            channel.basicPublish(RabbitConstantCode.EXCHANGE_TOPIC,
                    RabbitConstantCode.TOPIC_ROUTING_KEY_SEND_SMS_EMAIL,
                    null,
                    msg.getBytes(StandardCharsets.UTF_8));
            System.out.println("主题模式：" + msg);
        }

        if (!ObjectUtils.isEmpty(channel)) {
            channel.close();
        }

        if (!ObjectUtils.isEmpty(connection)) {
            connection.close();
        }
    }
}

