package com.xuecheng.framework.model.response;

import lombok.ToString;

/**
 * @Author: mrt.
 * @Description:
 * @Date:Created in 2018/1/24 18:33.
 * @Modified By:
 */

@ToString
public enum CommonCode implements ResultCode{
    /**
     * 操作是否成功
     */
    SUCCESS(true,10000,"操作成功！"),
    /**
     * 操作失败
     */
    FAIL(false,11111,"操作失败！"),
    /**
     * 此操作需要登陆系统
     */
    UNAUTHENTICATED(false,10001,"此操作需要登陆系统！"),
    /**
     * 权限不足，无权操作
     */
    LOW_AUTHORITY(false,10002,"权限不足，无权操作！"),
    /**
     * 参数异常
     */
    INVALID_PARAMS(false,10003,"参数异常！"),
    /**
     * 数学公式计算异常
     */
    INVALID_ARITHMETIC(false,10004,"数学公式计算异常！"),
    /**
     * 文件ID错误，MongoDB的GridFS没有相应的文件
     */
    ERROR_FILE_ID(false,10005,"文件ID错误，MongoDB的GridFS没有相应的文件！"),
    /**
     * RabbitMQ消息没有获取到PageID
     */
    RABBITMQ_MSG_PAGE_ID_IS_NULL(false,10005,"RabbitMQ消息没有获取到PageID！"),
    /**
     * 生成的静态html为空
     */
    CMS_GENERATE_STATIC_HTML_ISNULL(false,24005,"生成的静态html为空！"),
    /**
     * 抱歉，系统繁忙，请稍后重试
     */
    SERVER_ERROR(false,99999,"抱歉，系统繁忙，请稍后重试！");






    /**
     * 操作是否成功
     */
    boolean success;
    /**
     * 操作代码
     */
    int code;
    /**
     * 提示信息
     */
    String message;

    CommonCode(boolean success,int code, String message){
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
