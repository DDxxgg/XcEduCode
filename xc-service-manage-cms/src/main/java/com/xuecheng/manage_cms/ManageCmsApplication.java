package com.xuecheng.manage_cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description:
 * xc-service-manage-cms服务的启动类
 * 扫描接口,扫描本项目下的所有类
 * 扫描实体类
 * @author: DaiXG
 * @createTime: 2021-05-23 19:42:00 周日
 */
@SpringBootApplication
@EntityScan("com.xuecheng.framework.domain")
@ComponentScan(basePackages = {"com.xuecheng.api", "com.xuecheng.manage_cms", "com.xuecheng.framework"})
public class ManageCmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageCmsApplication.class, args);
    }

}
