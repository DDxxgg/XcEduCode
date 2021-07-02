package com.xuecheng.manage_cms.service.impl;

import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.exception.CustomExceptionCast;
import com.xuecheng.manage_cms.dao.CmsTemplateRepository;
import com.xuecheng.manage_cms.service.CmsTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @description: 页面模板的服务层接口实现类
 * @author: DaiXG
 * @createTime: 2021-06-12 12:47:00 周六
 */
@Service
public class CmsTemplateServiceImpl implements CmsTemplateService {

    @Autowired
    private CmsTemplateRepository cmsTemplateRepository;

    @Override
    public CmsTemplate findOneByTemplateId(String templateId) {
        //根据模板ID查询模板实体类，便于得到模板实体类里封装的
        Optional<CmsTemplate> cmsTemplateOptional = cmsTemplateRepository.findById(templateId);
        if (!cmsTemplateOptional.isPresent()) {
            CustomExceptionCast.cast(CmsCode.CMS_DATA_TEMPLATE_ISNULL);
        }
        //查询到模板的实体类
        return cmsTemplateOptional.get();
    }
}
