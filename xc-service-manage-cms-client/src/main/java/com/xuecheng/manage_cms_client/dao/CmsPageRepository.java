package com.xuecheng.manage_cms_client.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description: 页面管理操作的DAO层接口
 * @author: DaiXG
 * @createTime: 2021-05-23 20:22:00 周日
 */
@Repository
public interface CmsPageRepository extends MongoRepository<CmsPage, String> {

    /**
     * function 根据站点查询管理页面
     *
     * @param siteId 站点ID
     * @return java.util.List<com.xuecheng.framework.domain.cms.CmsPage>
     * @author DaiXG
     * @createTime 2021/5/23 21:50
     */
    List<CmsPage> findAllBySiteId(String siteId);


    /**
     * function 根据页面名称和页面别名来查询
     *
     * @param pageName  页面名称
     * @param pageAlias 页面别名
     * @return java.util.List<com.xuecheng.framework.domain.cms.CmsPage>
     * @author DaiXG
     * @createTime 2021/5/23 21:52
     */
    List<CmsPage> findAllByPageNameAndPageAlias(String pageName, String pageAlias);


    /**
     * function 某页面名称的页面数目
     *
     * @param pageName 页面名称
     * @return int
     * @author DaiXG
     * @createTime 2021/5/23 21:53
     */
    int countAllByPageName(String pageName);

    /**
     * function 三个参数确定了一个唯一页面，根据这三个参数查询页面
     *
     * @param pageName    页面名称
     * @param siteId      站点ID
     * @param pageWebPath 访问地址
     * @return com.xuecheng.framework.domain.cms.CmsPage
     * @author DaiXG
     * @createTime 2021/5/25 15:42
     */
    CmsPage findAllByPageNameAndSiteIdAndPageWebPath(String pageName, String siteId, String pageWebPath);

}
