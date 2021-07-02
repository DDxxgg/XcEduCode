package com.gxg.farmer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description: Freemarker测试启动类
 * @author: DaiXG
 * @createTime: 2021-05-30 11:37:00 周日
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.xuecheng.framework"})
public class FreemarkerTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreemarkerTestApplication.class,args);
    }

}
