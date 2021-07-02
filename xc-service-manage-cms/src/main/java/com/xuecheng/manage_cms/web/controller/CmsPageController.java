package com.xuecheng.manage_cms.web.controller;

import com.xuecheng.api.controller.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.service.CmsPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 页面管理的接口实现类
 * @author: DaiXG
 * @createTime: 2021-05-23 19:54:00 周日
 */


@RestController
@RequestMapping("/cms/page")
public class CmsPageController implements CmsPageControllerApi {

    @Autowired
    private CmsPageService cmsPageService;

    @Override
    @GetMapping("/list/{pageNo}/{pageSize}")
    public QueryResponseResult findList(@PathVariable("pageNo") int pageNo,
                                        @PathVariable("pageSize") int pageSize,
                                        QueryPageRequest queryPageRequest) {
        return cmsPageService.findList(pageNo, pageSize, queryPageRequest);
    }


    @Override
    @PostMapping("/add")
    public CmsPageResult add(@RequestBody CmsPage cmsPage) {
        return cmsPageService.add(cmsPage);
    }

    @Override
    @PutMapping("/update")
    public CmsPageResult update(String id, CmsPage cmsPage) {
        return cmsPageService.update(id, cmsPage);
    }

    @Override
    @DeleteMapping("/delete")
    public ResponseResult delete(String id) {
        return cmsPageService.delete(id);
    }

}
