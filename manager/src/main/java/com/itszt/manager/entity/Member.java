package com.itszt.manager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//作用将前端传来的String类型的日期转化为Date类型
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date registTime;        /*注册日期*/
    private String  status;          /*会员状态*/
}
