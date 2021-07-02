package com.example.farmer.config;

import com.example.farmer.constant.RabbitConstantCode;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: RabbitMQ的配置类(采用topic主题模式) RabbitMQ测试的时候，配置的Bean类，
 * @author: DaiXG
 * @createTime: 2021-06-27 18:15:00 周日
 */

@Configuration
public class RabbitMqConfig {


    /**
     * function 交换机的配置，定义为topic主题模式
     *
     * @author DaiXG
     * @createTime 2021/6/27 18:27
     * @return org.springframework.amqp.core.Exchange
     */
    @Bean(RabbitConstantCode.EXCHANGE_TOPIC)
    public Exchange exchangeTopic() {
        System.out.println("创建交换机......");
        return ExchangeBuilder
                .topicExchange(RabbitConstantCode.EXCHANGE_TOPIC)
                .durable(true)
                .build();
    }


    /**
     * function 定义了一个队列，名称是"TopicSmsQueue"
     *
     * @author DaiXG
     * @createTime 2021/6/27 18:34
     * @return org.springframework.amqp.core.Queue
     */
    @Bean(RabbitConstantCode.TOPIC_SMS_QUEUE)
    public Queue smsQueue() {
        System.out.println("创建SMS队列......");
        return new Queue(RabbitConstantCode.TOPIC_SMS_QUEUE);
    }


    /**
     * function 定义了一个队列，名称是"TopicEmailQueue"
     *
     * @author DaiXG
     * @createTime 2021/6/27 18:34
     * @return org.springframework.amqp.core.Queue
     */
    @Bean(RabbitConstantCode.TOPIC_EMAIL_QUEUE)
    public Queue emailQueue() {
        System.out.println("创建EMAIL队列......");
        return new Queue(RabbitConstantCode.TOPIC_EMAIL_QUEUE);
    }


    /**
     * function 将"TopicSmsQueue"队列和交换机绑定
     *
     * @author DaiXG
     * @createTime 2021/6/27 18:40
     * @param queue 队列名称
     * @param exchange 交换机名称
     * @return org.springframework.amqp.core.Binding
     */
    public Binding bindSmsQueueAndExchangeTopic(
            @Qualifier(RabbitConstantCode.TOPIC_SMS_QUEUE) Queue queue,
            @Qualifier(RabbitConstantCode.EXCHANGE_TOPIC) Exchange exchange) {
        System.out.println("绑定交换机和SMS队列......");
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(RabbitConstantCode.TOPIC_SMS_ROUTING_KEY)
                .noargs();
    }


    /**
     * function 将"TopicEmailQueue"队列和交换机绑定
     *
     * @author DaiXG
     * @createTime 2021/6/27 18:40
     * @param queue 队列名称
     * @param exchange 交换机名称
     * @return org.springframework.amqp.core.Binding
     */
    public Binding bindEmailQueueAndExchangeTopic(
            @Qualifier(RabbitConstantCode.TOPIC_EMAIL_QUEUE) Queue queue,
            @Qualifier(RabbitConstantCode.EXCHANGE_TOPIC) Exchange exchange) {
        System.out.println("绑定交换机和EMAIL队列......");
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(RabbitConstantCode.TOPIC_EMAIL_ROUTING_KEY)
                .noargs();
    }



}
