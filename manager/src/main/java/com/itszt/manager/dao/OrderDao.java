package com.itszt.manager.dao;

import com.itszt.manager.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderDao {
    //1.查询所有订单信息
    public List<Order> findAllOrderList();
}
