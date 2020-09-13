package com.itszt.manager.serviceImpl;

import com.itszt.manager.dao.OrderDao;
import com.itszt.manager.entity.Order;
import com.itszt.manager.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;

    @Override
    public List<Order> findOrderList() {

        List<Order> list = orderDao.findAllOrderList();
        return list;
    }

    @Override
    public int insertOrder(Order order) {
        int rows=orderDao.insertOrder(order);
        return rows;
    }

    @Override
    public List<Order> findOrderByConditions(String startTime, String endTime,String orderId, String talentName, String workMenuId, String workType) {
        return orderDao.findOrderByConditions(startTime,endTime,orderId,talentName,workMenuId,workType);
    }

    @Override
    public List<Order> findOrderByOrderStatus(String orderStatus) {
        return orderDao.findOrderByOrderStatus(orderStatus);
    }


}
