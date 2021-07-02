package com.xuecheng.api.controller;

import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @description: 页面发布的接口
 * @author: DaiXG
 * @createTime: 2021-06-30 14:38:00 周三
 */
public interface PostPageControllerApi {

    /**
     * function 页面发布接口
     *
     * @author DaiXG
     * @createTime 2021/6/30 14:40
     * @param pageId 页面ID
     * @return com.xuecheng.framework.model.response.ResponseResult
     */
    @ApiOperation("页面发布接口")
    ResponseResult postPage(String pageId);
}
