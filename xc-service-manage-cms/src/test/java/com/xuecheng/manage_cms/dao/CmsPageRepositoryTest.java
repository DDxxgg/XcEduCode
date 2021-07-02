package com.xuecheng.manage_cms.dao;
import com.google.common.collect.Lists;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsPageParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ObjectUtils;

/**
 * @description: 页面管理DAO层接口的测试
 * @author: DaiXG
 * @createTime: 2021-05-23 20:26:00 周日
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsPageRepositoryTest {

    @Autowired
    private CmsPageRepository cmsPageRepository;

    /**
     * function 分页查询测试
     *
     * @author DaiXG
     * @createTime 2021/5/23 20:28 
     */
    @Test
    public void testFindPage() {
        int pageNo = 1;
        int pageSize = 10;
        //Pageable接口的实现类
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<CmsPage> page = cmsPageRepository.findAll(pageable);

        System.out.println(page);
        System.out.println("页码总页数：" + page.getTotalPages());
        System.out.println("数据总条数：" + page.getTotalElements());
        System.out.println("数据具体内容：" + page.getContent());
    }

    /**
     * function 添加数据的测试
     *
     * @author DaiXG
     * @createTime 2021/5/23 21:34
     */
    @Test
    public void testSave() {
        String siteId = "11";
        String pageName = "33";
        String pageWebPath = "44";
        CmsPage cmsPage = new CmsPage();
        cmsPage.setSiteId("11");
        cmsPage.setPageId("22");
        cmsPage.setPageName("33");
        cmsPage.setPageAlias("33");
        cmsPage.setPageWebPath("44");
        cmsPage.setPageParameter("33");
        cmsPage.setPagePhysicalPath("55");
        cmsPage.setPageType("6");
        cmsPage.setPageTemplate("33");
        cmsPage.setPageHtml("43434");
        cmsPage.setPageStatus("1");
        cmsPage.setPageCreateTime(new Date());
        cmsPage.setTemplateId("2121");
        cmsPage.setPageParams(Lists.newArrayList(new CmsPageParam("1","2")));
        cmsPage.setHtmlFileId("呵呵");
        cmsPage.setDataUrl("12121");
        CmsPage cmsPageResult = cmsPageRepository.findAllByPageNameAndSiteIdAndPageWebPath(pageName, siteId, pageWebPath);
        if (ObjectUtils.isEmpty(cmsPageResult)) {
            CmsPage savedCmsPage = cmsPageRepository.save(cmsPage);
            System.out.println("保存成功");
        }
        System.out.println("已经存在该页面，不能重复保存，请修改站点或页面名称或访问路径之后在保存");
    }

    /**
     * function 删除测试
     *
     * @author DaiXG
     * @createTime 2021/5/23 21:41
     */
    @Test
    public void testDelete() {
        cmsPageRepository.deleteById("22");
    }

    /**
     * function 修改
     *
     * @author DaiXG
     * @createTime 2021/5/23 21:44
     */
    @Test
    public void test4Update() {
        Optional<CmsPage> optionalCmsPage = cmsPageRepository.findById("11223344");
        if (optionalCmsPage.isPresent()) {
            CmsPage cmsPage = optionalCmsPage.get();
            cmsPage.setSiteId("11223344");
            cmsPageRepository.save(cmsPage);
        }
    }

    /**
     * function 测试自定义DAO方法
     *
     * @author DaiXG
     * @createTime 2021/5/23 21:49
     */
    @Test
    public void testByCustomConditions() {
        String pageName = "preview_4028e58161bd3b380161bd3bcd2f0000.html";
        String pageAlias = "课程预览页面";
        String siteId = "5a751fab6abb5044e0d19ea1";
        List<CmsPage> cmsPages = cmsPageRepository.findAllByPageNameAndPageAlias(pageName, pageAlias);
        List<CmsPage> cmsPageList = cmsPageRepository.findAllBySiteId(siteId);
        int count = cmsPageRepository.countAllByPageName(pageName);

        System.out.println(cmsPages);
        System.out.println(cmsPageList);
        System.out.println(count);
    }

    @Test
    public void testFindAll() {
        //如果一共只有8条数据(不足pageSize条)，而传入的pageNo>=1,就会从第二页开始查询，这个时候查询不到数据
        int pageNo = 0;
        int pageSize = 10;
        //条件匹配器，规定查询字段的查询规则，siteId和templateId精确查询；pageAlias模糊查询
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("siteId", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("templateId", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("pageId",ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("pageAlias", ExampleMatcher.GenericPropertyMatchers.contains());
        //封装查询条件
        CmsPage cmsPage = new CmsPage();
//        cmsPage.setSiteId("5a751fab6abb5044e0d19ea1");
//        cmsPage.setTemplateId("5a962bf8b00ffc514038fafa");
//        cmsPage.setPageAlias("33");
        cmsPage.setPageId("5aed94530e66185b64804c11");
        //查询条件和条件匹配器
        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);
        //分页
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        //查询
        Page<CmsPage> page = cmsPageRepository.findAll(example, pageable);
        System.out.println(page);
        System.out.println("总页数：" + page.getTotalPages());
        System.out.println("总条数：" + page.getTotalElements());
        System.out.println("数据详情：" + page.getContent());
    }

}
