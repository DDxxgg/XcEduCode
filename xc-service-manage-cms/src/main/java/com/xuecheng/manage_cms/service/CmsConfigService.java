package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsConfig;

/**
 * @description: 页面数据模型服务接口，负责数据模型的增删改查等基本操作
 * @author: DaiXG
 * @createTime: 2021-05-30 17:49:00 周日
 */
public interface CmsConfigService {

    /**
     * function 根据主键ID查询数据模型
     *
     * @param id 数据模型的主键ID
     * @return com.xuecheng.framework.domain.cms.CmsConfig
     * @author DaiXG
     * @createTime 2021/5/30 17:50
     */
    CmsConfig getModel(String id);
}
