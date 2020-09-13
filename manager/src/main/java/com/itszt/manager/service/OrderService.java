package com.itszt.manager.service;

import com.itszt.manager.entity.Order;

import java.util.Date;
import java.util.List;

public interface OrderService {
   /* 1.查询所有订单信息*/
    public List<Order> findOrderList();

    /*2.新增订单信息*/
    public int insertOrder(Order order);

    /*3.根据多种条件查询订单信息*/
    List<Order> findOrderByConditions(String startTime, String endTime,
                                      String orderId,String talentName,
                                      String workMenuId,String workType);

    /*根据订单状态来查询订单信息*/
    public List<Order> findOrderByOrderStatus(String orderStatus);
}
