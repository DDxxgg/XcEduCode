package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsConfig;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @description: 页面数据模型DAO层接口
 * @author: DaiXG
 * @createTime: 2021-05-30 17:48:00 周日
 */
@Repository
public interface CmsConfigRepository extends MongoRepository<CmsConfig, String> {

}
