package com.gxg.farmer;

import com.google.common.collect.Maps;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.*;
import java.util.Map;


/**
 * @description: freemarker的测试方法
 * @author: DaiXG
 * @createTime: 2021-05-30 16:27:00 周日
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestFreemarker {

    /**
     * function 将定义好的*.ftl模板文件和模型数据，静态化生成html文件
     *
     * @author DaiXG
     * @createTime 2021/5/30 16:57
     */
    @Test
    public void testGenerateFreemarkerContent() throws IOException, TemplateException {
        //freemarker包，创建配置类
        Configuration configuration = new Configuration(Configuration.getVersion());
        //设置模板路径(根目录)
        String classPath = this.getClass().getResource("/").getPath();
        //设置模板路径(模板文件所在路径)
        configuration.setDirectoryForTemplateLoading(new File(classPath + "/templates/"));
        //设置模板字符
        configuration.setDefaultEncoding("UTF-8");
        //获取模板文件
        Template template = configuration.getTemplate("test01.ftl");
        //设置数据模型
        Map<String, Object> maps = Maps.newHashMapWithExpectedSize(1);
        maps.put("name", "润之");
        //获取静态化之后的内容
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, maps);
        System.out.println(content);
        //生成输入流，将java程序内容加载到内存
        InputStream inputStream = IOUtils.toInputStream(content);
        //输出流，将流文件从内加载到硬盘
        OutputStream outputStream = new FileOutputStream("D:\\10-Test\\test01.html");
        IOUtils.copy(inputStream, outputStream);
    }

    /**
     * function 将html模板用字符串在程序里表示，再结合数据模型，生成静态化内容
     *
     * @author DaiXG
     * @createTime 2021/5/30 17:00
     */
    @Test
    public void test() throws IOException, TemplateException {
        //freemarker包，创建配置类
        Configuration configuration = new Configuration(Configuration.getVersion());
        String templateString = "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>freemarker test</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div>\n" +
                "        hello,${name}\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
        String templateName = "template";
        //配置模板加载器
        StringTemplateLoader templateLoader = new StringTemplateLoader();
        templateLoader.putTemplate(templateName,templateString);
        configuration.setTemplateLoader(templateLoader);
        //得到配置好的模板
        Template template = configuration.getTemplate(templateName, "UTF-8");
        //数据模型
        Map<String, Object> maps = Maps.newHashMapWithExpectedSize(1);
        maps.put("name", "恩来");
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, maps);
        System.out.println(content);
        //生成输入流，将java程序内容加载到内存
        InputStream inputStream = IOUtils.toInputStream(content);
        //输出流，将流文件从内加载到硬盘
        OutputStream outputStream = new FileOutputStream("D:\\10-Test\\test02.html");
        IOUtils.copy(inputStream, outputStream);
    }
}
