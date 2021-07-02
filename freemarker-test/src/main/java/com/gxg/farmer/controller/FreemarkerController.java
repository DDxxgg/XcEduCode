package com.gxg.farmer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.util.Map;

/**
 * @description: FreeMarker的测试类   @Controller跳转到页面；@RestController返回数据；
 * @author: DaiXG
 * @createTime: 2021-05-30 11:24:00 周日
 */
@Controller
@RequestMapping("/freemarker")
public class FreemarkerController {

    @Resource(name = "restTemplate")
    private RestTemplate restTemplate;

    @RequestMapping("/test01")
    public String freemarker(Map<String,Object> map) {
        map.put("name","齐天大圣");
        return "test01";
    }

    @RequestMapping("/banner")
    public String banner(Map<String,Object> map) {
        //轮播图数据模型地址
        String dataUrl = "http://localhost:31001/cms/config/model/5a791725dd573c3574ee333f";
        ResponseEntity<Map> entity = restTemplate.getForEntity(dataUrl, Map.class);
        Map body = entity.getBody();
        if (!CollectionUtils.isEmpty(body)) {
           map.putAll(body);
        }
        map.put("name","美猴王");
        //classPath:/templates下的模板文件
        return "index_banner";
    }
}
