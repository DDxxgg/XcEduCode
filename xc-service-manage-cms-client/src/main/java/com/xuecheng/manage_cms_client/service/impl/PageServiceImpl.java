package com.xuecheng.manage_cms_client.service.impl;

import com.xuecheng.framework.common.GridFsCommon;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.exception.CustomExceptionCast;
import com.xuecheng.manage_cms_client.dao.CmsPageRepository;
import com.xuecheng.manage_cms_client.dao.CmsSiteRepository;
import com.xuecheng.manage_cms_client.service.PageService;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;

/**
 * @description: 这个服务的功能主要是完成页面发布
 *               获取到MQ的消息后，将静态化页面从GridFS下载到服务指定的物理路径
 * @author: DaiXG
 * @createTime: 2021-06-30 11:08:00 周三
 */
@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private CmsPageRepository cmsPageRepository;
    @Autowired
    private CmsSiteRepository cmsSiteRepository;
    @Autowired
    private GridFsCommon gridFsCommon;


    /**
     * function 根据页面id将静态化的html页面保存到服务器指定的路径,这就是页面的发布
     *
     * @author DaiXG
     * @createTime 2021/6/30 13:43
     * @param pageId 页面id
     */
    @SneakyThrows
    @Override
    public void saveStaticHtmlPageToServerPath(String pageId) {
        Optional<CmsPage> optionalCmsPage = cmsPageRepository.findById(pageId);
        if (!optionalCmsPage.isPresent()) {
            CustomExceptionCast.cast(CmsCode.CMS_PAGE_NOT_EXIST);
        }
        //根据页面id获取到的页面
        CmsPage cmsPage = optionalCmsPage.get();

        //指定服务器路径用来存储静态化Html文件
        String siteId = cmsPage.getSiteId();
        Optional<CmsSite> optionalCmsSite = cmsSiteRepository.findById(siteId);
        if (!optionalCmsSite.isPresent()) {
            CustomExceptionCast.cast(CmsCode.CMS_SITE_NOT_EXIST);
        }
        CmsSite cmsSite = optionalCmsSite.get();

        //从GridFS下载静态Html文件到服务器的物理路径
        String serverPath = cmsSite.getSiteWebPath() +
                            cmsPage.getPagePhysicalPath() +
                            cmsPage.getPageName();

        //静态文件存储在GridFS里的的id值
        String htmlFileId = cmsPage.getHtmlFileId();
        InputStream inputStream = gridFsCommon.getStaticFileStreamByHtmlFileId(htmlFileId);
        //输出流，将文件保存到指定路径下
        OutputStream outputStream = new FileOutputStream(serverPath);
        IOUtils.copy(inputStream,outputStream);
    }
}
