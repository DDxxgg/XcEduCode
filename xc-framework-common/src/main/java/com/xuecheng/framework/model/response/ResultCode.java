package com.xuecheng.framework.model.response;

/**
 *
 * @author mrt
 * @date 2018/3/5
 * 10000-- 通用错误代码
 * 22000-- 媒资错误代码
 * 23000-- 用户中心错误代码
 * 24000-- cms错误代码
 * 25000-- 文件系统
 */
public interface ResultCode {
    /**
     * 操作是否成功,true为成功，false操作失败
     *
     * @return: boolean
     * @author: DaiXG
     * @createtime: 2021/5/23 17:32
     */
    boolean success();
    /**
     * 操作代码
     *
     * @return: int
     * @author: DaiXG
     * @createtime: 2021/5/23 17:33
     */
    int code();
    /**
     * 提示信息
     *
     * @return: java.lang.String
     * @author: DaiXG
     * @createtime: 2021/5/23 17:33
     */
    String message();

}
