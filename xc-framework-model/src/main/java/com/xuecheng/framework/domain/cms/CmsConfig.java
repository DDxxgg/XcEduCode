package com.xuecheng.framework.domain.cms;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Dai
 * @Description: CMS页面模板对应的数据模型配置类
 * @Date:Created in 2018/1/24 10:04.
 * @Modified By:
 */
@Data
@ToString
@Document(collection = "cms_config")
public class CmsConfig implements Serializable {

    private static final long serialVersionUID = -11649260296046320L;
    /**
     * 主键ID
     */
    @Id
    private String id;
    /**
     * 数据模型名称
     */
    private String name;
    /**
     * 数据模型项目内容
     */
    private List<CmsConfigModel> model;

}
