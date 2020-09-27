package com.itszt.manager.service;

import com.itszt.manager.entity.Order;
import com.itszt.manager.entity.PageObject;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderService {



   /* 1.查询所有订单信息*/
    public PageObject<Order> findOrderList(Integer pageCurrent);

    /*2.新增订单信息*/
    public int insertOrder(Order order);

    /*3.根据多种条件查询订单信息*/
    List<Order> findOrderByConditions(String startTime, String endTime,
                                      String orderId,String talentName,
                                      String workMenuId,String workType);

    /*根据订单状态来查询订单信息*/
    public List<Order> findOrderByOrderStatus(String orderStatus);


}
