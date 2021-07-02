package com.xuecheng.manage_cms_client.dao;

import com.xuecheng.framework.domain.cms.CmsSite;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @description: 站点操作的DAO层接口
 * @author: DaiXG
 * @createTime: 2021-06-30 10:55:00 周三
 */
@Repository
public interface CmsSiteRepository extends MongoRepository<CmsSite, String> {
}
