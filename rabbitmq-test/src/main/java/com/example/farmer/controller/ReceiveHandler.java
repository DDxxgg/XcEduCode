package com.example.farmer.controller;

import com.example.farmer.constant.RabbitConstantCode;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description: 监听队列的服务
 * @author: DaiXG
 * @createTime: 2021-06-27 18:52:00 周日
 */
@Component
public class ReceiveHandler {

    /**
     * function 监听email队列
     *
     * @author DaiXG
     * @createTime 2021/6/27 18:54
     * @param msg 生产者的消息
     * @param message 2
     * @param channel 通道
     */
    @RabbitListener(queues = {RabbitConstantCode.TOPIC_EMAIL_QUEUE})
    public void receive_email(String msg, Message message, Channel channel){
        System.out.println(msg);
    }

    /**
     * function 监听sms队列
     *
     * @author DaiXG
     * @createTime 2021/6/27 18:54
     * @param msg 生产者的消息
     * @param message 2
     * @param channel 通道
     */
    @RabbitListener(queues = {RabbitConstantCode.TOPIC_SMS_QUEUE})
    public void receive_sms(String msg,Message message,Channel channel){
        System.out.println(msg);
    }
}
