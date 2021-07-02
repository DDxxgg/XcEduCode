package com.xuecheng.framework.exception;

import com.xuecheng.framework.model.response.ResultCode;

/**
 * @description: 异常抛出类
 * @author: DaiXG
 * @createTime: 2021-05-25 18:14:00 周二
 */
public class CustomExceptionCast {

    /**
     * function 自定义异常抛出方法
     *
     * @author DaiXG
     * @createTime 2021/5/25 18:16
     * @param resultCode 异常信息
     * @return void
     */
    public static void cast(ResultCode resultCode) {
        throw new CustomException(resultCode);
    }
}
