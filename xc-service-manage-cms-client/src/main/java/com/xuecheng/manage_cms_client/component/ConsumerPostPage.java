package com.xuecheng.manage_cms_client.component;

import com.google.gson.Gson;
import com.xuecheng.framework.exception.CustomExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.manage_cms_client.service.PageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @description: 监听RabbitMQ，拿到MQ传递的pageId消息，调用下载保存静态页面的方法，完成页面发布
 * @author: DaiXG
 * @createTime: 2021-06-30 13:50:00 周三
 */
@Component
public class ConsumerPostPage {
    private static final Logger LOGGER = LoggerFactory.
            getLogger(ConsumerPostPage.class);

    @Autowired
    private PageService pageService;
    @Autowired
    private Gson gson;


    /**
     * function 监听RabbitMQ的队列queue_of_cms_post_page，拿到消息后发布页面
     *
     * @author DaiXG
     * @createTime 2021/6/30 14:11
     * @param msg 1
     * @throws
     * @return void
     */
    @RabbitListener(queues = {"${xuecheng.mq.queue}"})
    public void postPage(String msg) {
        LOGGER.info("==========监听queue_of_cms_post_page队列获取到的信息,{}==========",msg);
        Map map = gson.fromJson(msg, Map.class);
        String key = "pageId";
        String pageId = "";
        if (map.containsKey(key)) {
            pageId = (String)map.get(key);
        }
        if (StringUtils.isEmpty(pageId)) {
            CustomExceptionCast.cast(CommonCode.RABBITMQ_MSG_PAGE_ID_IS_NULL);
        }
        System.out.println("收到的消息：" + msg);
        pageService.saveStaticHtmlPageToServerPath(pageId);
    }
}
