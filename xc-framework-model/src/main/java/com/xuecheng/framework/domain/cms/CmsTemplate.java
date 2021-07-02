package com.xuecheng.framework.domain.cms;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @Author: mrt.
 * @Description:
 * @Date:Created in 2018/1/24 10:04.
 * @Modified By:
 */
@Data
@ToString
@Document(collection = "cms_template")
public class CmsTemplate implements Serializable {

    private static final long serialVersionUID = 8601112933314234856L;
    /**
     * 站点ID
     */
    private String siteId;
    /**
     * 模版ID
     */
    @Id
    private String templateId;
    /**
     * 模版名称
     */
    private String templateName;
    /**
     * 模版参数
     */
    private String templateParameter;

    /**
     * 模版文件Id(将制作好的模板ftl文件保存到GridFs里的唯一标识,这个字段就对应这个唯一标识)
     */
    private String templateFileId;
}
