package com.example.farmer.queue.workqueue;

import com.example.farmer.constant.RabbitConstantCode;
import com.example.farmer.util.RabbitMqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import lombok.SneakyThrows;
import org.springframework.util.ObjectUtils;

import java.nio.charset.StandardCharsets;

/**
 * @description: 工作队列模式 生产者01测试
 * @author: DaiXG
 * @createTime: 2021-06-12 21:40:00 周六
 */
public class ProducerWorkQueue {

    @SneakyThrows
    public static void main(String[] args) {
        //服务端(消息生产者或消息消费者都是服务端)，与broker(可以认为是MQ的服务端)之间的连接
        Connection connection = null;
        //服务端(消息生产者或消息消费者都是服务端)，与broker(可以认为是MQ的服务端)之间的通道，由连接来创建
        Channel channel = null;

        try {
            //创建一个与MQ的链接
            connection = RabbitMqUtil.getConnection();

            //创建与交换机的通道，每一个通道就是一次会话
            channel = connection.createChannel();

            /*
             * 生明队列,如果这个队列在MQ中不存在，就创建这个新队列
             *
             * @author DaiXG
             * @createTime 2021/6/12 21:54
             * @param args 声明队列名称为：WorkQueue
             * @param args 是否持久化
             * @param args 队列是否独占此队列
             * @param args 队列不再使用时是否自动删除此队列
             * @param args 队列的参数
             */
            channel.queueDeclare(
                    RabbitConstantCode.WORK_QUEUE,
                    true,
                    false,
                    false,
                    null);

            int cycleNum = 10;
            for (int i = 1; i <= cycleNum; i++) {
                //6、指定生产者发送的消息内容
                String msgContent = System.currentTimeMillis() + "-" + i;
                /*
                 * 有了连接和通道，也有了交换机(MQ默认的交换机)和消息队列,生产者和Broker之间就可以通信发送消息了；
                 * 由通道将消息发送交换机，交换机根据路由规则，将消息发送到相应的队列
                 *
                 * @author DaiXG
                 * @createTime 2021/6/12 22:08
                 * @param args 交换机名称，使用的是MQ自己默认的交换机
                 * @param args routingKey, 取队列名称，表示交换机通过这个路由将消息转发到该队列(HelloWorld队列)
                 * @param args 消息包含的属性
                 * @param args 消息的二进制文件
                 */
                channel.basicPublish(
                        "",
                        RabbitConstantCode.WORK_QUEUE,
                        null,
                        msgContent.getBytes(StandardCharsets.UTF_8));

                System.out.println("生产者消息发送成功：" + msgContent);
            }
        } catch (Exception e) {
            System.out.println("总做队列模式生产者发送消息出现异常......");
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
