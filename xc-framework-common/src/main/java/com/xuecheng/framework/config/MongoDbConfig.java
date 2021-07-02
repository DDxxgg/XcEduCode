package com.xuecheng.framework.config;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

/**
 * @description: MongoDB的配置类
 * @author: DaiXG
 * @createTime: 2021-06-11 16:27:00 周五
 */

@Configuration
public class MongoDbConfig {
    /**
     * 指定数据库名称
     */
    private static final String MongoDB = "xc_cms";

    /**
     * function GridFsTemplate的作用，针对存储在MongoDB的文件数据进行增删查操作
     *
     * @param dbFactory MongoDB工厂
     * @param converter MongoDB转换器
     * @return org.springframework.data.mongodb.gridfs.GridFsTemplate
     * @author DaiXG
     * @createTime 2021/6/11 16:51
     */
    @Bean(name = "gridFsTemplate")
    public GridFsTemplate gridFsTemplate(MongoDbFactory dbFactory, MongoConverter converter) {
        return new GridFsTemplate(dbFactory, converter, "fs");
    }

    /**
     * function 使用这个GridFsTemplate对象存储数据会生成一个bucket名称为test_fs的桶
     *
     * @param dbFactory MongoDB工厂
     * @param converter MongoDB转换器
     * @return org.springframework.data.mongodb.gridfs.GridFsTemplate
     * @author DaiXG
     * @createTime 2021/6/11 16:43
     */
    @Bean(name = "gridFsTestTemplate")
    public GridFsTemplate gridFsTestTemplate(MongoDbFactory dbFactory, MongoConverter converter) {
        return new GridFsTemplate(dbFactory, converter, "test_fs");
    }


    /**
     * function GridFSBucket作用用于打开存储在MongoDB数据库中的文件流
     *
     * @param mongoClient 1
     * @return com.mongodb.client.gridfs.GridFSBucket
     * @throws
     * @author DaiXG
     * @createTime 2021/6/11 16:58
     */
    @Bean(name = "gridFsBucket")
    public GridFSBucket gridFSBucket(MongoClient mongoClient) {
        MongoDatabase database = mongoClient.getDatabase(MongoDB);
        return GridFSBuckets.create(database);
    }
}
