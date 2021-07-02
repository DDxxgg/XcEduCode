package com.xuecheng.manage_cms.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: RabbitMQ的配置类 作为生产方，只需要配置和消息消费方同一个交换机
 * @author: DaiXG
 * @createTime: 2021-06-30 14:30:00 周三
 */
@Configuration
public class RabbitmqConfig {
    /**
     * 该配置类中交换机的Bean名称
     */
    private static final String BEAN_EXCHANGE_OF_CMS_POST_PAGE = "bean_exchange_of_cms_post_page";

    /**
     * 交换机名称
     */
    @Value("${xuecheng.mq.exchange}")
    public String exchangeDirect;


    /**
     * 声明交换机
     *
     * @author DaiXG
     * @createTime 2021/6/30 14:34
     * @return org.springframework.amqp.core.Exchange
     */
    @Bean(BEAN_EXCHANGE_OF_CMS_POST_PAGE)
    public Exchange exchangeDirect() {
        return ExchangeBuilder.directExchange(exchangeDirect).durable(true).build();
    }

}
