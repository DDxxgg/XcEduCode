package com.xuecheng.framework.domain.cms.request;

import com.xuecheng.framework.model.request.RequestData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @description: 页面查询请求参数的封装类,请求参数封装类统一继承RequestData；
 *               分页查询同意使用QueryResponseResult来作为响应类型
 * @author: DaiXG
 * @createTime: 2021-05-23 17:22:00 周日
 */
@Data
@ToString
public class QueryPageRequest extends RequestData {

    /**
     * 站点ID
     */
    @ApiModelProperty("站点ID")
    private String siteId;
    /**
     * 页面ID
     */
    @ApiModelProperty("页面ID")
    private String pageId;
    /**
     * 模板ID
     */
    @ApiModelProperty("模板ID")
    private String templateId;
    /**
     * 页面名称
     */
    @ApiModelProperty("页面名称")
    private String pageName;
    /**
     * 页面别名
     */
    @ApiModelProperty("页面别名")
    private String pageAlias;
}
