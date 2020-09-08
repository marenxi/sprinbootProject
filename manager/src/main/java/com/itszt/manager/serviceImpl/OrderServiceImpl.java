package com.itszt.manager.serviceImpl;

import com.itszt.manager.dao.OrderDao;
import com.itszt.manager.entity.Order;
import com.itszt.manager.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;

    @Override
    public List<Order> findOrderList() {

                List<Order> list=orderDao.findAllOrderList();
                return list;
    }
}
