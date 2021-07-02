package com.example.farmer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description: RabbitMQ生产者 服务启动类
 * @author: DaiXG
 * @createTime: 2021-06-12 21:31:00 周六
 */

@SpringBootApplication
public class RabbitMqApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMqApplication.class,args);
    }
}
