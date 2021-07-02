package com.xuecheng.manage_cms_client.service;

/**
 * @description: 这个服务的功能主要是完成页面发布
 *               获取到MQ的消息后，将静态化页面从GridFS下载到服务指定的物理路径
 * @author: DaiXG
 * @createTime: 2021-06-30 11:04:00 周三
 */
public interface PageService {

    /**
     * function 根据页面id将静态化的html页面保存到服务器指定的路径
     *
     * @author DaiXG
     * @createTime 2021/6/30 11:07
     * @param pageId 页面ID
     */
    void saveStaticHtmlPageToServerPath(String pageId);
}
