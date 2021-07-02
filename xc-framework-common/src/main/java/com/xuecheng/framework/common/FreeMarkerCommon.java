package com.xuecheng.framework.common;

import com.xuecheng.framework.exception.CustomExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @description: FreeMarker的一些公共操作方法
 * @author: DaiXG
 * @createTime: 2021-06-30 14:24:00 周三
 */
@Component
public class FreeMarkerCommon {

    /**
     * function 将模板和模型数据组装到一起，生成静态化文件
     *
     * @author DaiXG
     * @createTime 2021/6/30 14:45
     * @param template 模板
     * @param dataModel 模型数据
     * @param templateName 自定义的模板名称
     * @return java.lang.String
     */
    @SneakyThrows
    public String executeStaticHtml(String template, Map dataModel,String templateName) {
        Configuration configuration = new Configuration(Configuration.getVersion());
        StringTemplateLoader templateLoader = new StringTemplateLoader();
        templateLoader.putTemplate(templateName, template);
        configuration.setTemplateLoader(templateLoader);
        configuration.setDefaultEncoding("UTF-8");

        Template pageTemplate = configuration.getTemplate(templateName);
        String staticContent = FreeMarkerTemplateUtils.processTemplateIntoString(pageTemplate, dataModel);
        if (StringUtils.isEmpty(staticContent)) {
            CustomExceptionCast.cast(CommonCode.CMS_GENERATE_STATIC_HTML_ISNULL);
        }
        return staticContent;
    }
}
