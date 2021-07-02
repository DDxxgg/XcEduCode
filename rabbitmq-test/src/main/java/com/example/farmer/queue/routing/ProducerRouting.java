package com.example.farmer.queue.routing;

import com.example.farmer.constant.RabbitConstantCode;
import com.example.farmer.util.RabbitMqUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import lombok.SneakyThrows;
import org.springframework.util.ObjectUtils;

import java.nio.charset.StandardCharsets;

/**
 * @description: RoutingKey模式，生产者测试
 * @author: DaiXG
 * @createTime: 2021-06-26 22:56:00 周六
 */
public class ProducerRouting {

    @SneakyThrows
    public static void main(String[] args) {
        Connection connection = null;
        Channel channel = null;
        try {
            //声明一个与MQ的链接
            connection = RabbitMqUtil.getConnection();

            //创建与交换机的通道，每一个通道就是一次会话
            channel = connection.createChannel();

            /*
             * function 声明一个交换机
             *
             * @param args 交换机名称
             * @param args 交换机类型 路由模式选择DIERECT
             */
            channel.exchangeDeclare(RabbitConstantCode.EXCHANGE_DIRECT,
                    BuiltinExchangeType.DIRECT);

            /*
             * function 声明队列
             *
             * @param args 指定队列的名称
             * @param args 是否持节话
             * @param args 是否独占此队列
             * @param args 队列不用是否删除
             * @param args 参数
             */
            channel.queueDeclare(RabbitConstantCode.DIRECT_SMS_QUEUE,
                    true,
                    false,
                    false,
                    null);
            //声明队列(发送邮件)
            channel.queueDeclare(RabbitConstantCode.DIRECT_EMAIL_QUEUE,
                    true,
                    false,
                    false,
                    null);

            /*
             * function 绑定队列和交换机，并指定路由名称是队列名称
             *
             * @param args 队列名称
             * @param args 交换机名称
             * @param args 路由名称，默认和队列名称同名
             */
            channel.queueBind(RabbitConstantCode.DIRECT_SMS_QUEUE,
                    RabbitConstantCode.EXCHANGE_DIRECT,
                    RabbitConstantCode.DIRECT_SMS_ROUTING_KEY);
            //将交换机和队列(邮件)绑定起来
            channel.queueBind(RabbitConstantCode.DIRECT_EMAIL_QUEUE,
                    RabbitConstantCode.EXCHANGE_DIRECT,
                    RabbitConstantCode.DIRECT_EMAIL_ROUTING_KEY);

            int num = 10;
            for (int i = 1; i <= num; i++) {
                String msg = "邮件消息-" + i;
                /*
                 * function 面向交换机发送消息
                 *
                 * @param args 交换机名称
                 * @param args 路由key(routingKey),绑定队列和交换机的时候指定了路由，通过这里的路由名称，可以将消息转发到相应的队列
                 * @param args 消息属性
                 * @param args 消息内容
                 */
                channel.basicPublish(RabbitConstantCode.EXCHANGE_DIRECT,
                        RabbitConstantCode.DIRECT_EMAIL_ROUTING_KEY,
                        null,
                        msg.getBytes(StandardCharsets.UTF_8));
                System.out.println("路由模式邮件发送消息成功：" + msg);
            }

            for (int i = 1; i <= num; i++) {
                String msg = "手机短信消息-" + i;
                channel.basicPublish(RabbitConstantCode.EXCHANGE_DIRECT,
                        RabbitConstantCode.DIRECT_SMS_ROUTING_KEY,
                        null,
                        msg.getBytes(StandardCharsets.UTF_8));
                System.out.println("路由模式短信发送消息成功：" + msg);
            }
        } catch (Exception exception) {
            System.out.println("路由模式发送消息失败......");
        } finally {
            if (!ObjectUtils.isEmpty(channel)) {
                channel.close();
            }
            if (!ObjectUtils.isEmpty(connection)) {
                connection.close();
            }
        }
    }
}
