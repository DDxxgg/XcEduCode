package com.xuecheng.manage_cms_client.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: RabbitMQ的配置类 作为消费方，需要配置交换机、队列和路由名称
 * @author: DaiXG
 * @createTime: 2021-06-30 10:24:00 周三
 */
@Configuration
public class RabbitmqConfig {
    /**
     * 该配置类中交换机的Bean名称
     */
    private static final String BEAN_EXCHANGE_OF_CMS_POST_PAGE = "bean_exchange_of_cms_post_page";

    /**
     * 该配置类中队列的Bean名称
     */
    private static final String BEAN_QUEUE_OF_CMS_POST_PAGE = "bean_queue_of_cms_post_page";

    /**
     * 队列名称
     */
    @Value("${xuecheng.mq.queue}")
    private String queueNameOfCmsPostPage;
    /**
     * 路由名称
     */
    @Value("${xuecheng.mq.routingkey}")
    private String routingKey;
    /**
     * 交换机名称
     */
    @Value("${xuecheng.mq.exchange}")
    private String exchangeDirect;


    /**
     * 声明交换机
     *
     * @author DaiXG
     * @createTime 2021/6/30 10:47
     * @return org.springframework.amqp.core.Exchange
     */
    @Bean(BEAN_EXCHANGE_OF_CMS_POST_PAGE)
    public Exchange exchangeDirect() {
        return ExchangeBuilder.directExchange(exchangeDirect).durable(true).build();
    }

    /**
     * function 声明队列
     *
     * @author DaiXG
     * @createTime 2021/6/30 10:47
     * @return org.springframework.amqp.core.Queue
     */
    @Bean(BEAN_QUEUE_OF_CMS_POST_PAGE)
    public Queue queue() {
        return new Queue(queueNameOfCmsPostPage);
    }


    /**
     * function 绑定队列和交换机
     *
     * @author DaiXG
     * @createTime 2021/6/30 10:50
     * @return org.springframework.amqp.core.Binding
     */
    public Binding bindQueueAndExchange(@Qualifier(BEAN_QUEUE_OF_CMS_POST_PAGE) Queue queue,
                                        @Qualifier(BEAN_EXCHANGE_OF_CMS_POST_PAGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey).noargs();
    }




}
