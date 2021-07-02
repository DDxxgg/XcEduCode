package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsTemplate;

/**
 * @description: 页面模板的服务层接口
 * @author: DaiXG
 * @createTime: 2021-06-12 12:46:00 周六
 */
public interface CmsTemplateService {

    /**
     * function 根据模板Id查询页面模板
     *
     * @param templateId 1
     * @return com.xuecheng.framework.domain.cms.CmsTemplate
     * @author DaiXG
     * @createTime 2021/6/12 12:47
     */
    CmsTemplate findOneByTemplateId(String templateId);
}
