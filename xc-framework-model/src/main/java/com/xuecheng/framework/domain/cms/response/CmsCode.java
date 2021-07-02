package com.xuecheng.framework.domain.cms.response;

import com.xuecheng.framework.model.response.ResultCode;
import lombok.ToString;

/**
 *
 * @author mrt
 * @date 2018/3/5
 */
@ToString
public enum CmsCode implements ResultCode {


    /**
     * 页面ID值错误，页面不存在
     */
    CMS_PAGE_NOT_EXIST(false,24000,"页面ID值错误，页面不存在！"),
    /**
     *页面名称已存在
     */
    CMS_PAGE_EXIST(false,24001,"页面已经存在！"),
    /**
     * 从页面信息中找不到获取数据的url
     */
    CMS_GENERATE_HTML_DATA_URL_ISNULL(false,24002,"从页面信息中找不到获取数据的url！"),
    /**
     * 根据页面的数据url获取不到数据
     */
    CMS_GENERATE_HTML_DATA_ISNULL(false,24003,"根据页面的数据url获取不到数据！"),
    /**
     * 页面模板为空
     */
    CMS_GENERATE_HTML_TEMPLATE_ISNULL(false,24004,"页面模板为空！"),
    /**
     * 生成的静态html为空
     */
    CMS_GENERATE_STATIC_HTML_ISNULL(false,24005,"生成的静态html为空！"),
    /**
     * 保存静态html出错
     */
    CMS_GENERATE_HTML_SAVE_HTML_ERROR(false,24005,"保存静态html出错！"),
    /**
     * 预览页面为空
     */
    CMS_COURSE_PREVIEW_ISNULL(false,24007,"预览页面为空！"),
    /**
     * 页面数据模型为空
     */
    CMS_DATA_MODEL_ISNULL(false,24008,"页面的数据模型为空！"),
    /**
     * 页面对应的的模板实体类为空
     */
    CMS_DATA_TEMPLATE_ISNULL(false,24009,"页面对应的的模板实体类为空！"),
    /**
     * 页面对应的的模板文件为空
     */
    CMS_DATA_TEMPLATE_FILE_ISNULL(false,24010,"页面对应的的模板文件为空！"),
    /**
     * 站点不存在
     */
    CMS_SITE_NOT_EXIST(false,24000,"站点ID值错误，站点不存在！");

    /**
     * 操作状态
     */
    boolean success;
    /**
     * 操作状态代码
     */
    int code;
    /**
     * 提示信息
     */
    String message;
    /**
     * function CmsCode构造方法
     *
     * @author DaiXG
     * @createTime 2021/5/29 20:50
     * @param success 1
     * @param code 2
     * @param message 3
     */
    CmsCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
