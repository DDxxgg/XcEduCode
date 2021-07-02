package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @description: 页面模板DAO层接口
 * @author: DaiXG
 * @createTime: 2021-06-12 12:05:00 周六
 */
@Repository
public interface CmsTemplateRepository extends MongoRepository<CmsTemplate, String> {

}
