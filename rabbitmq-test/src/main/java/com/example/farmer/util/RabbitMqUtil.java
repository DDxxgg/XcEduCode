package com.example.farmer.util;

import com.example.farmer.constant.RabbitConstantCode;
import com.rabbitmq.client.*;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;


/**
 * @description: RabbitMQ的工具类
 * @author: DaiXG
 * @createTime: 2021-06-14 09:20:00 周一
 */
public class RabbitMqUtil {

    /**
     * function 生产者获取连接
     *
     * @return com.rabbitmq.client.Connection
     * @throws Exception 异常
     * @author DaiXG
     * @createTime 2021/6/14 9:25
     */
    public static Connection getConnection() throws Exception {

        //1、定义连接工厂并指定相关属性
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //本机VMWare安装的Linux服务器ip地址，CentOS7安装了Docker,Docker上部署了RabbitMQ容器
        connectionFactory.setHost(RabbitConstantCode.HOST);
        //MQ服务的端口
        connectionFactory.setPort(RabbitConstantCode.PORT);
        //用户名
        connectionFactory.setUsername(RabbitConstantCode.USERNAME);
        //密码
        connectionFactory.setPassword(RabbitConstantCode.PASSWORD);
        //rabbitMQ默认的虚拟主机名称就是"/"，相当于一个独立的MQ服务器
        connectionFactory.setVirtualHost(RabbitConstantCode.VIRTUALHOST);
        //2、创建连接
        return connectionFactory.newConnection();
    }

    /**
     * function 管道对应的消费者
     *
     * @param channel 管道
     * @return com.rabbitmq.client.Consumer
     * @author DaiXG
     * @createTime 2021/6/14 9:44
     */
    public static Consumer getConsumer(Channel channel) {
        return new DefaultConsumer(channel) {
            /**
             * function 消费者调用此方法来接收消息
             *
             * @author DaiXG
             * @createTime 2021/6/12 22:43
             * @param consumerTag 消费者的标签
             * @param envelope 消息包的内容，可以获取消息ID、路由和交换机
             * @param properties 1
             * @param body 消息体的二进制数据
             */
            @SneakyThrows
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) {
                System.out.println("交换机：" + envelope.getExchange());
                System.out.println("路由：" + envelope.getRoutingKey());
                System.out.println("消息ID：" + envelope.getDeliveryTag());
                System.out.println("消费者收到的消息：" + new String(body, StandardCharsets.UTF_8));
            }
        };
    }
}
