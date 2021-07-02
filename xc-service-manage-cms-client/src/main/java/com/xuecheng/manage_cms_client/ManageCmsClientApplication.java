package com.xuecheng.manage_cms_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description: xc-service-manage-cms-client页面管理客户端 启动类 这个服务相当于MQ的消息消费方；
 * xc-service-manage-cms 页面管理服务 启动类  这个服务相当于MQ的消息提供方。
 * 根据路由从相应的队列拿到MQ的消息(站点)，到GridFS里下载静态化的Html文件,
 * 并将这个文件存在xc-service-manage-cms-client服务指定的物理路径里。
 *
 * @author: DaiXG
 * @createTime: 2021-06-30 10:09:00 周三
 * @EntityScan: 扫描实体类
 * @ComponentScan: 扫描common下的所有类/扫描com.xuecheng.manage_cms_client下的所有类
 */

@SpringBootApplication
@EntityScan("com.xuecheng.framework.domain")
@ComponentScan(basePackages = {"com.xuecheng.framework", "com.xuecheng.manage_cms_client"})
public class ManageCmsClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageCmsClientApplication.class, args);
    }
}
