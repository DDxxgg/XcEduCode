package com.gxg.farmer.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @description: Student的pojo类
 * @author: DaiXG
 * @createTime: 2021-05-30 11:14:00 周日
 */

@Data
@ToString
public class Student implements Serializable {
    private static final long serialVersionUID = 3967723081785027721L;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 钱包
     */
    private Float money;
    /**
     * 朋友
     */
    private List<Student> friends;
    /**
     * 最好的朋友
     */
    private Student bestFriends;

}
