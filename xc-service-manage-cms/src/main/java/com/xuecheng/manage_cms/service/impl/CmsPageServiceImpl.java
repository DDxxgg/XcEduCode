package com.xuecheng.manage_cms.service.impl;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import com.xuecheng.framework.common.FreeMarkerCommon;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.exception.CustomExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import com.xuecheng.manage_cms.service.CmsPageService;
import com.xuecheng.manage_cms.service.CmsTemplateService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

/**
 * @description: 页面管理服务
 * @author: DaiXG
 * @createTime: 2021-05-23 22:07:00 周日
 */
@Service
public class CmsPageServiceImpl implements CmsPageService {

    @Autowired
    private CmsPageRepository cmsPageRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CmsTemplateService cmsTemplateService;
    @Resource(name = "gridFsTemplate")
    private GridFsTemplate gridFsTemplate;
    @Resource
    private GridFSBucket gridFsBucket;
    @Autowired
    private FreeMarkerCommon freeMarkerCommon;

    @Override
    public CmsPage findCmsPageByPageId(String pageId) {
        Optional<CmsPage> cmsPageOptional = cmsPageRepository.findById(pageId);
        if (!cmsPageOptional.isPresent()) {
            CustomExceptionCast.cast(CmsCode.CMS_PAGE_NOT_EXIST);
        }
        return cmsPageOptional.get();
    }

    @Override
    public QueryResponseResult findList(int pageNo, int pageSize, QueryPageRequest queryPageRequest) {
        //条件匹配器，条件查询规则
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("siteId", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("templateId", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("pageAlias", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("pageId", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("pageName", ExampleMatcher.GenericPropertyMatchers.contains());
        if (ObjectUtils.isEmpty(queryPageRequest)) {
            queryPageRequest = new QueryPageRequest();
        }
        //封装查询条件值
        CmsPage cmsPage = new CmsPage();
        if (!StringUtils.isEmpty(queryPageRequest.getSiteId())) {
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }
        if (!StringUtils.isEmpty(queryPageRequest.getTemplateId())) {
            cmsPage.setTemplateId(queryPageRequest.getTemplateId());
        }
        if (!StringUtils.isEmpty(queryPageRequest.getPageAlias())) {
            cmsPage.setPageAlias(queryPageRequest.getPageAlias());
        }
        if (!StringUtils.isEmpty(queryPageRequest.getPageName())) {
            cmsPage.setPageName(queryPageRequest.getPageName());
        }
        if (!StringUtils.isEmpty(queryPageRequest.getPageId())) {
            cmsPage.setPageId(queryPageRequest.getPageId());
        }
        //结合查询条件值条件匹配器
        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);
        //分页，映射一下：前端pageNo从1开始，后端pageNo从0开始(查询第一页的数据)
        pageNo = pageNo <= 0 ? 0 : pageNo - 1;
        pageSize = pageSize <= 0 ? 20 : pageSize;
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        //分页条件查询
        Page<CmsPage> page = cmsPageRepository.findAll(example, pageable);

        QueryResult<CmsPage> queryResult = new QueryResult<>();
        queryResult.setTotal(page.getTotalElements());
        queryResult.setList(page.getContent());

        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    @Override
    public CmsPageResult add(CmsPage cmsPage) {
        //int num = 10 / 0;
        if (ObjectUtils.isEmpty(cmsPage)) {
            CustomExceptionCast.cast(CmsCode.CMS_PAGE_NOT_EXIST);
        }
        //先确定该页面是否存在
        CmsPage cmsPageSearched = cmsPageRepository.findAllByPageNameAndSiteIdAndPageWebPath(
                cmsPage.getPageName(), cmsPage.getSiteId(), cmsPage.getPageWebPath());

        if (!ObjectUtils.isEmpty(cmsPageSearched)) {
            CustomExceptionCast.cast(CmsCode.CMS_PAGE_EXIST);
        }
        //页面主键，在保存页面实体类的时候，由mongodb自动生成
        cmsPage.setPageId(null);
        //保存页面
        CmsPage savedCmsPage = cmsPageRepository.save(cmsPage);
        return new CmsPageResult(CommonCode.SUCCESS, savedCmsPage);
    }

    @Override
    public CmsPageResult update(String id, CmsPage cmsPage) {
        Optional<CmsPage> optional = cmsPageRepository.findById(id);

        if (!optional.isPresent()) {
            CustomExceptionCast.cast(CmsCode.CMS_PAGE_NOT_EXIST);
        }
        CmsPage oldCmsPage = optional.get();
        cmsPage.setPageId(id);
        BeanUtils.copyProperties(cmsPage, oldCmsPage);
        CmsPage updateCmsPage = cmsPageRepository.save(oldCmsPage);
        return new CmsPageResult(CommonCode.SUCCESS, updateCmsPage);
    }

    @Override
    public ResponseResult delete(String id) {
        Optional<CmsPage> optional = cmsPageRepository.findById(id);

        if (!optional.isPresent()) {
            CustomExceptionCast.cast(CmsCode.CMS_PAGE_NOT_EXIST);
        }
        cmsPageRepository.deleteById(id);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @Override
    public String getPageStaticHtml(String pageId) throws Exception {
        //获取数据模型
        Map dataModel = this.getModelDate(pageId);
        //获取模板文件
        String pageTemplate = this.getPageTemplate(pageId);
        String templateName = "pageTemplate";
        //静态化操作，得到静态化后的html文件
        return freeMarkerCommon.executeStaticHtml(pageTemplate, dataModel,templateName);
    }

    /**
     * function 根据页面实体类里封装的数据模型地址,得到该页面数据模型
     *
     * @param pageId 页面ID
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author DaiXG
     * @createTime 2021/6/12 12:01
     */
    private Map getModelDate(String pageId) {
        CmsPage cmsPage = this.findCmsPageByPageId(pageId);
        ResponseEntity<Map> entity = restTemplate.getForEntity(cmsPage.getDataUrl(), Map.class);
        Map model = entity.getBody();
        if (ObjectUtils.isEmpty(model)) {
            CustomExceptionCast.cast(CmsCode.CMS_DATA_MODEL_ISNULL);
        }
        return model;
    }

    /**
     * function 根据页面实体类里封装的页面模板ID,得到该页面对应的模板
     *
     * @param pageId 页面ID
     * @return jang.lang.String
     * @author DaiXG
     * @createTime 2021/6/12 12:02
     */
    private String getPageTemplate(String pageId) throws IOException {
        CmsPage cmsPage = this.findCmsPageByPageId(pageId);
        CmsTemplate cmsTemplate = cmsTemplateService.findOneByTemplateId(cmsPage.getTemplateId());
        //获取到模板文件ID
        String templateFileId = cmsTemplate.getTemplateFileId();
        GridFSFile gridFsFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(templateFileId)));

        String template = "";
        if (!ObjectUtils.isEmpty(gridFsFile)) {
            GridFSUploadOptions options = new GridFSUploadOptions()
                    .chunkSizeBytes(358400);
            //GridFSDownloadStream继承InputStream(GridFSBucket用于打开文件流，将MongoDB数据库的文件转为输入流)
            GridFSDownloadStream gridFsDownloadStream = gridFsBucket
                    .openDownloadStream(gridFsFile.getObjectId());

            //创建GridFsResource，获取流对象
            GridFsResource gridFsResource = new GridFsResource(gridFsFile, gridFsDownloadStream);

            //获取流数据
            template = IOUtils.toString(gridFsResource.getInputStream(), StandardCharsets.UTF_8);
        }
        if (StringUtils.isEmpty(template)) {
            CustomExceptionCast.cast(CmsCode.CMS_DATA_TEMPLATE_FILE_ISNULL);
        }
        return template;
    }
}
