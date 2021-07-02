package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.framework.domain.cms.CmsConfigModel;
import com.xuecheng.manage_cms.service.CmsConfigService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @description: 页面静态化测试
 * @author: DaiXG
 * @createTime: 2021-06-11 18:00:00 周五
 */
public class PageStaticTest {

    @Autowired
    private CmsConfigService cmsConfigService;

    /**
     * function:
     * 本系统做业面的静态化，需要模板文件和模型数据，然后静态化生成页面上可以显示的html文件
     * 本系统：将模板的实体类和模板对应的html文件手动存储在MongoDB数据库中，不再写代码存储模板实体类、和模板文件
     * 模板实体(cms_template)封装有对应的模板文件ID(templateFileId),找到实体类就找到了其对应的模板文件
     * 该测试的目的：将模板文件根据文件ID从数据库取出来，再配合查询到的模型数据，静态化生成可以显示的html页面
     *             出发点都是从page页面开始，page页面的实体类包含有dataUrl,可以查询数据模型；
     *             page页面的实体类还有templateId，可以根据这个查询模板实体类，模板实体类包含有模板文件ID,可以查询出存储在MongoDB的模板文件
     *
     *             至此，模板文件和数据模型都已经拿到，就可以页面静态化操作，得到可以显示的html文件
     *
     * @author DaiXG
     * @createTime 2021/6/11 17:29
     */
    @Test
    public void testPageStatic() {


    }
}
