package com.xuecheng.manage_cms.dao;

import com.mongodb.client.gridfs.GridFSBucket;
import com.xuecheng.framework.common.GridFsCommon;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @description: GridFsTemplate的测试
 * @author: DaiXG
 * @createTime: 2021-06-11 16:06:00 周五
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class GridFsTemplateTest {

    @Resource(name = "gridFsTemplate")
    private GridFsTemplate gridFsTemplate;

    @Resource(name = "gridFsTestTemplate")
    private GridFsTemplate gridFsTestTemplate;

    @Autowired
    private GridFsCommon gridFsCommon;

    /**
     * function 用gridFsTemplate(指定了bucket名称fs,用来测试保存文件到MongoDB数据库)
     *
     * @author DaiXG
     * @createTime 2021/6/11 17:14
     */
    @Test
    public void testGridFsStore() throws FileNotFoundException {
        //定义要存储的文件并将之定义为输入流
        FileInputStream fis = new FileInputStream("D:\\10-Test\\出师表.txt");
        ObjectId objectId = gridFsTemplate.store(fis, "出师表01", "");
        String fileId = objectId.toString();
        System.out.println(fileId);
    }


    /**
     * function 用gridFsTemplate(指定了bucket名称test_fs,用来测试保存文件到MongoDB数据库)
     *
     * @author DaiXG
     * @createTime 2021/6/11 17:17
     */
    @Test
    public void testGridFsTestStore() throws FileNotFoundException {
        //定义要存储的文件并将之定义为输入流
        FileInputStream fis = new FileInputStream("D:\\10-Test\\index_banner.ftl");
        ObjectId objectId = gridFsTestTemplate.store(fis, "轮播图", "轮播图信息");
        String fileId = objectId.toString();
        System.out.println(fileId);
    }


    /**
     * function 用gridFsTemplate根据文件id从MongoDB数据库里查询出来文件
     * gridFSBucket将文件转为流对象
     * IOUtils(commons-io)将流转为字符串
     *
     * @author DaiXG
     * @createTime 2021/6/11 17:17
     */
    @Test
    public void testGridFsBucket() throws IOException {
        String fileId = "5a7719d76abb5042987eec3a";
        //根据文件id获取到存储在MongoDB的文件
        InputStream inputStream = gridFsCommon.getStaticFileStreamByHtmlFileId(fileId);
        String content = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        System.out.println(content);
    }

    @Test
    public void testGridFsTemplateDelete() {
        //用gridFsTestTemplate根据文件Id删除"test_fs"里的数据   test_fs.files和test_file.chunks的数据
        gridFsTestTemplate.delete(Query.query(Criteria.where("_id").is("60c322f21310231d0c462603")));
    }



}
