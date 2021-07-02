package com.xuecheng.framework.model.response;

import lombok.Data;
import lombok.ToString;

/**
 * @description: 分页查询的响应数据模型(页面 、 课程等 ， QueryResult里封装的List < T > 数据列表是泛型数据)
 * @author: DaiXG
 * @createTime: 2021-05-25 15:35:00 周二
 */
@Data
@ToString
public class QueryResponseResult extends ResponseResult {

    QueryResult queryResult;

    public QueryResponseResult(ResultCode resultCode, QueryResult queryResult) {
        super(resultCode);
        this.queryResult = queryResult;
    }

}
