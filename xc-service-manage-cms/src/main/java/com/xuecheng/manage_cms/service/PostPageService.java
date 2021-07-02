package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.model.response.ResponseResult;
import lombok.SneakyThrows;

/**
 * @description: 该系统作为页面发布的发起方，负责页面静态化并将静态化文件
 * @author: DaiXG
 * @createTime: 2021-06-30 14:16:00 周三
 */
public interface PostPageService {

    /**
     * function 根据页面id将页面静态化，将静态化的页面保存到GridFS；
     * 同时发布RabbitMQ消息，xc-service-manage-cms-client会监听消息队列，从GridFS中下载静态化后的页面，
     * 并将页面保存到服务器的指定路径。
     *
     * @author DaiXG
     * @createTime 2021/6/30 15:05
     * @param pageId 页面id
     * @return com.xuecheng.framework.model.response.ResponseResult
     */
    ResponseResult postPage(String pageId);
}
