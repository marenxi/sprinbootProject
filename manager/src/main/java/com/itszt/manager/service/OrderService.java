package com.itszt.manager.service;

import com.itszt.manager.entity.Order;

import java.util.List;

public interface OrderService {
    //1.查询所有订单信息
    public List<Order> findOrderList();
}
