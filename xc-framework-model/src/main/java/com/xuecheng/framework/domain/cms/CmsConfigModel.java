package com.xuecheng.framework.domain.cms;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

/**
 * @Author: Dai
 * @Description: CMS页面模板对应的数据模型配置类
 * @Date:Created in 2018/1/24 10:04.
 * @Modified By:
 */
@Data
@ToString
public class CmsConfigModel implements Serializable {
    private static final long serialVersionUID = -434593627874621704L;
    /**
     * 数据模型key值
     */
    private String key;
    /**
     * 数据模型名字
     */
    private String name;
    /**
     * 数据模型url
     */
    private String url;
    /**
     * 数据模型项目内容-复杂值
     */
    private Map<String,Object> mapValue;
    /**
     * 数据模型项目内容-简单值
     */
    private String value;

}
