package com.xuecheng.manage_cms.service.impl;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.xuecheng.framework.common.GridFsCommon;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import com.xuecheng.manage_cms.service.CmsPageService;
import com.xuecheng.manage_cms.service.PostPageService;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @description: 该系统作为页面发布的发起方，负责页面静态化并将静态化文件
 * @author: DaiXG
 * @createTime: 2021-06-30 15:07:00 周三
 */
@Service
public class PostPageServiceImpl implements PostPageService {

    @Autowired
    private CmsPageService cmsPageService;
    @Autowired
    private GridFsCommon gridFsCommon;
    @Autowired
    private CmsPageRepository cmsPageRepository;
    @Autowired
    private Gson gson;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${xuecheng.mq.exchange}")
    private String exchangeName;

    /**
     * function 根据页面id将页面静态化，将静态化的页面保存到GridFS；
     * 同时发布RabbitMQ消息，xc-service-manage-cms-client会监听消息队列，从GridFS中下载静态化后的页面，
     * 并将页面保存到服务器的指定路径。
     *
     * @author DaiXG
     * @createTime 2021/6/30 15:08
     * @param pageId 页面id
     * @return com.xuecheng.framework.model.response.ResponseResult
     */
    @SneakyThrows
    @Override
    public ResponseResult postPage(String pageId) {
        //获取静态化文件
        String staticPageHtml = cmsPageService.getPageStaticHtml(pageId);
        //根据CmsPage的htmlFileId是否为空，来判断GridFS是否已经保存了该页面的静态化文件，如果保存了，先删掉
        CmsPage cmsPage = cmsPageService.findCmsPageByPageId(pageId);
        //如果已经有静态化文件存在里面了，就先删除这个静态化文件，保存新的静态化文件
        if (!StringUtils.isEmpty(cmsPage.getHtmlFileId())) {
            gridFsCommon.deleteFile(cmsPage.getHtmlFileId());
        }
        //保存静态化文件，并获取到静态文件htmlFileId
        String fileId = gridFsCommon.storeStaticFile(staticPageHtml, cmsPage.getPageName());
        //将静态文件htmlFileId保存到CmsPage里面,并跟新CmsPage
        cmsPage.setHtmlFileId(fileId);
        cmsPageRepository.save(cmsPage);
        //组装要发送的消息
        Map<String,Object> map = Maps.newHashMapWithExpectedSize(1);
        map.put("pageId",cmsPage.getPageId());
        String jsonMsg = gson.toJson(map);
        //指定交换机和路由，给RabbitMQ发送消息(这个交换机和路由与消费方队列、交换机绑定时的交换机是同一个，路由也是完全匹配)
        rabbitTemplate.convertSendAndReceive(exchangeName,cmsPage.getSiteId(),jsonMsg);
        return new ResponseResult(CommonCode.SUCCESS);
    }
}
