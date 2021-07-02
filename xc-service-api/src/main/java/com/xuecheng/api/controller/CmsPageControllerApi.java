package com.xuecheng.api.controller;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @description: 页面查询接口
 * @author: DaiXG
 * @createTime: 2021-05-23 18:10:00 周日
 */
@Api(value = "CMS页面管理接口",tags = "CMS页面管理接口，负责页面的增删改查")
public interface CmsPageControllerApi {

    /**
     * function 分页查询配置的页面列表
     *
     * @author DaiXG
     * @createTime 2021/5/23 18:31
     * @param pageNum 1
     * @param pageSize 2
     * @param queryPageRequest 3
     * @return com.xuecheng.framework.model.response.QueryResponseResult
     */
    @ApiOperation("分页查询CMS页面")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo",value = "页码",
                    required = true,paramType = "path",dataType = "int"),
            @ApiImplicitParam(name = "pageSize",value = "每页的记录数",
                    required = true,paramType = "path",dataType = "int")
    })
    QueryResponseResult findList(int pageNum, int pageSize,
                                 QueryPageRequest queryPageRequest);

    /**
     * function 保存CMS页面
     *
     * @author DaiXG
     * @createTime 2021/5/25 16:07
     * @param cmsPage 需要保存的页面页面
     * @return com.xuecheng.framework.domain.cms.response.CmsPageResult
     */
    @ApiOperation("新增CMS页面")
    CmsPageResult add(CmsPage cmsPage);


    /**
     * function 跟新页面
     *
     * @author DaiXG
     * @createTime 2021/5/25 16:45
     * @param id 主键ID
     * @param cmsPage 更新的页面实体类
     * @return com.xuecheng.framework.domain.cms.response.CmsPageResult
     */
    @ApiOperation("修改CMS页面")
    @ApiImplicitParam(name = "id",value = "页面主键ID",
            required = true,paramType = "query",dataType = "String")
    CmsPageResult update(String id,CmsPage cmsPage);

    /**
     * function 根据主键删除ID
     *
     * @author DaiXG
     * @createTime 2021/5/25 17:35
     * @param id 1
     * @return com.xuecheng.framework.domain.cms.response.CmsPageResult
     */
    @ApiOperation("删除CMS页面")
    @ApiImplicitParam(name = "id",value = "页面主键ID",
            required = true,paramType = "query",dataType = "String")
    ResponseResult delete(String id);


}
