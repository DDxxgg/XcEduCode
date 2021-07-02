package com.xuecheng.api.controller;

import com.xuecheng.framework.domain.cms.CmsConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @description: 数据模型配置类接口
 * @author: DaiXG
 * @createTime: 2021-05-30 17:42:00 周日
 */
@Api(value = "CMS页面数据模型管理接口",tags = "CMS页面数据模型管理接口，负责数据模型的增删改")
public interface CmsConfigControllerApi {

    /**
     * function 根据ID获取数据模型   model/{id},数据类型必须为paramType = "path"
     *
     * @author DaiXG
     * @createTime 2021/5/30 17:44
     * @param id 数据模型主键ID
     * @return com.xuecheng.framework.domain.cms.CmsConfig
     */
    @ApiOperation("根据模型主键ID页面数据模型的")
    @ApiImplicitParam(name = "id",value = "页面数据模型主键ID",
            required = true,paramType = "path",dataType = "String")
    CmsConfig getModel(String id);
}
