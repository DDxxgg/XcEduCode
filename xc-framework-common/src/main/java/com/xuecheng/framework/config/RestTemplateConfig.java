package com.xuecheng.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @description: RestTemplate的配置类
 * @author: DaiXG
 * @createTime: 2021-06-11 16:25:00 周五
 */


@Configuration
public class RestTemplateConfig {

    /**
     * function 服务启动的时候，创建RestTemplate，并且交由给Spring容器管理来创建对象
     *
     * @return org.springframework.web.client.RestTemplate
     * @author DaiXG
     * @createTime 2021/5/30 18:23
     */
    @Bean(name = "restTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }
}
