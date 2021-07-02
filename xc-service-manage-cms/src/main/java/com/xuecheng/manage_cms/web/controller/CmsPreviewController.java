package com.xuecheng.manage_cms.web.controller;

import com.xuecheng.manage_cms.service.CmsPageService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * 页面静态化后的预览，此时页面还没有部署到服务器上
 * 只需要把静态化后的内容展示到浏览器页面
 * @author: DaiXG
 * @createTime: 2021-06-12 13:01:00 周六
 */

@Controller
public class CmsPreviewController {

    @Autowired
    private CmsPageService cmsPageService;

    @SneakyThrows
    @RequestMapping("/cms/preview/{pageId}")
    public void preview(@PathVariable("pageId") String pageId, HttpServletResponse response) {
        String pageStaticHtml = cmsPageService.getPageStaticHtml(pageId);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(pageStaticHtml.getBytes());
    }

}
