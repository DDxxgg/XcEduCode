package com.xuecheng.framework.common;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.xuecheng.framework.exception.CustomExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.io.InputStream;

/**
 * @description: MongoDB的GridFS文件储存系统通用代码
 * @author: DaiXG
 * @createTime: 2021-06-30 11:52:00 周三
 */

@Component
public class GridFsCommon {

    @Resource(name = "gridFsTemplate")
    private GridFsTemplate gridFsTemplate;

    @Resource(name = "gridFsBucket")
    private GridFSBucket gridFsBucket;

    /**
     * function 根据静态文件ID,从GridFS里将静态文件转为输入流
     *
     * @author DaiXG
     * @createTime 2021/6/30 12:03
     * @param htmlFileId 1
     * @throws
     * @return java.io.InputStream
     */
    @SneakyThrows
    public InputStream getStaticFileStreamByHtmlFileId(String htmlFileId) {
        GridFSFile gridFsFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(htmlFileId)));
        if (ObjectUtils.isEmpty(gridFsFile)) {
            CustomExceptionCast.cast(CommonCode.ERROR_FILE_ID);
        }
        GridFSDownloadStream gridFsDownloadStream = gridFsBucket.openDownloadStream(gridFsFile.getObjectId());
        GridFsResource gridFsResource = new GridFsResource(gridFsFile, gridFsDownloadStream);
        return gridFsResource.getInputStream();
    }


    /**
     * function 将静态化的内容保存到GridFS,并返回文件fileId
     *
     * @author DaiXG
     * @createTime 2021/6/30 15:30
     * @param staticContent 静态化后页面内容
     * @param pageName 静态化页面的名称
     * @return java.lang.String
     */
    public String storeStaticFile(String staticContent,String pageName) {
        InputStream inputStream = IOUtils.toInputStream(staticContent);
        ObjectId objectId = gridFsTemplate.store(inputStream, pageName);
        return objectId.toString();
    }

    /**
     * function 删除静态化文件
     *
     * @author DaiXG
     * @createTime 2021/6/30 15:37 
     * @param htmlFileId 静态化文件Id
     * @throws 
     * @return void
     */
    public void deleteFile(String htmlFileId) {
        gridFsTemplate.delete(Query.query(Criteria.where("_id").is(htmlFileId)));
    }
}
