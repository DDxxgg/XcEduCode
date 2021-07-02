package com.example.farmer.queue.publicsubscrib;

import com.example.farmer.constant.RabbitConstantCode;
import com.example.farmer.util.RabbitMqUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import lombok.SneakyThrows;
import org.springframework.util.ObjectUtils;

/**
 * @description: 发布订阅模式 生产者
 * @author: DaiXG
 * @createTime: 2021-06-26 13:15:00 周六
 */
public class ProducerPublishSubscribe {
    @SneakyThrows
    public static void main(String[] args) {
        Connection connection = null;
        Channel channel = null;
        try {
            //创建一个与MQ的链接
            connection = RabbitMqUtil.getConnection();

            //创建与交换机的通道，每个通道是一次会话
            channel = connection.createChannel();

            /*
             * function 声明交换机
             *
             * @param args 交换机名称
             * @param args 交换机类型 fanout,topic,direct,headers
             */
            channel.exchangeDeclare(RabbitConstantCode.EXCHANGE_FANOUT,
                    BuiltinExchangeType.FANOUT);

            /*
             * function 声明队列(发送短信) 如果队列不存在，就创建新队列
             *
             * @param args 队列名称
             * @param args 是否持久化
             * @param args 是否独占此队列
             * @param args 队列不用是否删除
             * @param args 参数
             */
            channel.queueDeclare(RabbitConstantCode.FANOUT_SMS_QUEUE,
                    true,
                    false,
                    false,
                    null);
            //声明队列(发送邮件)
            channel.queueDeclare(RabbitConstantCode.FANOUT_EMAIL_QUEUE,
                    true,
                    false,
                    false,
                    null);

            /*
             * function 将交换机和队列(短信)绑定起来
             *
             * @param args 队列名称
             * @param args 交换机名称
             * @param args 路由key
             */
            channel.queueBind(RabbitConstantCode.FANOUT_SMS_QUEUE,
                    RabbitConstantCode.EXCHANGE_FANOUT,
                    "");
            //将交换机和队列(邮件)绑定起来
            channel.queueBind(RabbitConstantCode.FANOUT_EMAIL_QUEUE,
                    RabbitConstantCode.EXCHANGE_FANOUT,
                    "");

            //循环次数
            int num = 10;
            for (int i = 1; i <= num; i++) {
                String msgContent = "消息-" + i;
                /*
                 * function 面向交换机发送消息
                 *
                 * @param args 交换机名称，如果不指定(比如工作队列模式)，会使用MQ底层默认的交换机
                 * @param args 路由key(routingKey),通过key名称将消息转发到具体的队列
                 * @param args 消息属性
                 * @param args 消息内容
                 */
                channel.basicPublish(RabbitConstantCode.EXCHANGE_FANOUT,
                        "",
                        null,
                        msgContent.getBytes());
                System.out.println("生产者消息发送成功：" + msgContent);
            }
        } catch (Exception exception) {
            System.out.println("发布订阅模式，生产者生产消息异常......");
        } finally {
            if (!ObjectUtils.isEmpty(channel)) {
                channel.close();
            }
            if (!ObjectUtils.isEmpty(connection)) {
                connection.createChannel();
            }
        }
    }
}
