package com.itszt.manager.dao;

import com.itszt.manager.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface OrderDao {
    //1.查询所有订单信息
    public List<Order> findAllOrderList();

    /*2.新增订单信息*/
    public int insertOrder(Order order);

    /*根据多种条件查询订单信息*/
   public List<Order> findOrderByConditions(@Param("startTime") String startTime,@Param("endTime")  String endTime,
                                      @Param("orderId") String orderId,@Param("talentName") String talentName,
                                      @Param("workMenuId") String workMenuId,@Param("workType")String workType);

   /*根据订单状态来查询订单信息*/
   public List<Order> findOrderByOrderStatus(String orderStatus);
}
