package com.itszt.manager.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.sql.DataSourceDefinition;
import java.math.BigDecimal;
import java.util.Date;

@ToString
@Data
public class Order {
    private Integer id;         /*序号*/
    private String orderId;     /*订单编号*/
    private String talentName;  /*达人名称*/
    private String workMenuId;  /*工单编号*/
    private String workType;    /*工种*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//作用将前端传来的String类型的日期转化为Date类型
    private Date workTime;      /*工作时间*/
    private BigDecimal orderAmount; /*订单金额*/
    private String orderStatus;     /*订单状态*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//作用将前端传来的String类型的日期转化为Date类型
    private Date orderTime;         /*下单时间*/


}
