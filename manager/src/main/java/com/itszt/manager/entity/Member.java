package com.itszt.manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Data
@Slf4j
@ToString
public class Member {
    private Integer id;
    private String name;             /*姓名*/
    private Integer age;             /*年龄*/
    private String workType;         /*工种*/
    private String telephone;        /*电话*/
    private Date registTime;        /*注册日期*/
    private String  status;          /*会员状态*/
}
