package com.xuecheng.manage_cms.dao;

import com.xuecheng.manage_cms.ManageCmsApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @description: RestTemplate远程接口调用测试
 *               测试的时候加上@RunWith和@SpringBootTest两个注解，以便启动Spring容器，管理创建实体类对象
 * @author: DaiXG
 * @createTime: 2021-05-30 18:28:00 周日
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManageCmsApplication.class)
public class RestTemplateTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testRestTemplate() {
        //轮播图的数据模型
        String url = "http://localhost:31001/cms/config/model/5a791725dd573c3574ee333f";
        //注意接口所在的服务必须要启动起来
        ResponseEntity<Map> entity = restTemplate.getForEntity(url, Map.class);
        System.out.println(entity);
    }
}

