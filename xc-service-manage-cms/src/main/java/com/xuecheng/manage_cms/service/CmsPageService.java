package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;

/**
 * @description: 页面管理服务
 * @author: DaiXG
 * @createTime: 2021-05-23 22:11:00 周日
 */
public interface CmsPageService {

    /**
     * function 根据页面ID查询页面实体
     *
     * @param pageId 页面ID
     * @return com.xuecheng.framework.domain.cms.CmsPage
     * @author DaiXG
     * @createTime 2021/6/12 12:42
     */
    CmsPage findCmsPageByPageId(String pageId);

    /**
     * function 页面分页查询
     *
     * @param pageNo           页码数
     * @param pageSize         每一页的条数
     * @param queryPageRequest 封装的请求参数，查询条件
     * @return com.xuecheng.framework.model.response.QueryResponseResult
     * @author DaiXG
     * @createTime 2021/5/25 14:24
     */
    QueryResponseResult findList(int pageNo, int pageSize, QueryPageRequest queryPageRequest);

    /**
     * function 保存页面，先判断该页面是否存在，不存在就保存；存在的话就提示
     *
     * @param cmsPage 需要保存的页面实体类作为参数
     * @return com.xuecheng.framework.domain.cms.response.CmsPageResult
     * @author DaiXG
     * @createTime 2021/5/25 15:56
     */
    CmsPageResult add(CmsPage cmsPage);

    /**
     * function 修改页面
     *
     * @param id      主键ID
     * @param cmsPage 更新的页面实体类
     * @return com.xuecheng.framework.domain.cms.response.CmsPageResult
     * @author DaiXG
     * @createTime 2021/5/25 16:47
     */
    CmsPageResult update(String id, CmsPage cmsPage);

    /**
     * function 根据主键删除CMS页面
     *
     * @param id 1
     * @return com.xuecheng.framework.model.response.ResponseResult
     * @author DaiXG
     * @createTime 2021/5/25 17:39
     */
    ResponseResult delete(String id);

    /**
     * function 根据页面ID,进行页面静态化操作
     *
     * @param pageId 页面ID
     * @return java.lang.String
     * @author DaiXG
     * @createTime 2021/6/11 18:26
     */
    String getPageStaticHtml(String pageId) throws Exception;


}
