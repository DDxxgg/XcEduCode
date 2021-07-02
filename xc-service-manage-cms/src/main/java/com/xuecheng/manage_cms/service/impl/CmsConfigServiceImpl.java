package com.xuecheng.manage_cms.service.impl;

import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.exception.CustomExceptionCast;
import com.xuecheng.manage_cms.dao.CmsConfigRepository;
import com.xuecheng.manage_cms.service.CmsConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @description: 页面数据模型服务接口实现类，负责数据模型的增删改查等基本操作
 * @author: DaiXG
 * @createTime: 2021-05-30 17:51:00 周日
 */
@Service
public class CmsConfigServiceImpl implements CmsConfigService {

    @Autowired
    private CmsConfigRepository cmsConfigRepository;

    @Override
    public CmsConfig getModel(String id) {
        Optional<CmsConfig> cmsConfigOptional = cmsConfigRepository.findById(id);
        if (!cmsConfigOptional.isPresent()) {
            CustomExceptionCast.cast(CmsCode.CMS_DATA_MODEL_ISNULL);
        }
        return cmsConfigOptional.get();
    }
}
