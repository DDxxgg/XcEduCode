package com.xuecheng.framework.exception;

import com.google.common.collect.ImmutableMap;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: 异常捕获类 注意在项目启动类上@ComponentScan添加这个类所在的扫描包
 * @author: DaiXG
 * @createTime: 2021-05-25 18:17:00 周二
 */
@ControllerAdvice
public class ExceptionCatch {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionCatch.class);
    /**
     * 使用builder来构建一个异常类型和错误代码的异常
     */
    protected static ImmutableMap.Builder<Class<? extends Throwable>, ResultCode> builder = ImmutableMap.builder();
    /**
     * 使用EXCEPTIONS存放异常类型和错误代码的映射，ImmutableMap的特点的一旦创建不可改变，并且线程安全
     */
    private static ImmutableMap<Class<? extends Throwable>, ResultCode> EXCEPTIONS = builder.build();

    static {
        //在这里加入一些基础的异常类型判断
        builder.put(HttpMessageNotReadableException.class, CommonCode.INVALID_PARAMS);
        builder.put(ArithmeticException.class, CommonCode.INVALID_ARITHMETIC);
    }

    /**
     * function 自定义异常捕获方法
     *
     * @author DaiXG
     * @createTime 2021/5/25 18:23
     * @param exception 自定义异常
     * @return com.xuecheng.framework.model.response.ResponseResult
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseResult catchCustomException(CustomException exception) {
        LOGGER.error("捕获到自定义异常，{}",exception.getMessage(),exception);
        return new ResponseResult(exception.getResultCode());
    }


    /**
     * function 不可预知异常捕获方法
     *
     * @author DaiXG
     * @createTime 2021/5/29 22:16
     * @param exception 不可预知异常
     * @return com.xuecheng.framework.model.response.ResponseResult
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult catchException(Exception exception) {
        LOGGER.error("捕获到异常：" + exception.getMessage());
        //在这里加入一些基础的异常类型判断
        ResultCode resultCode = EXCEPTIONS.get(exception.getClass());
        return new ResponseResult(ObjectUtils.isEmpty(resultCode) ? CommonCode.SERVER_ERROR : resultCode);
    }
}
