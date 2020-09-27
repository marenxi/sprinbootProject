package com.itszt.manager.serviceImpl;

import com.itszt.manager.dao.OrderDao;
import com.itszt.manager.entity.Order;
import com.itszt.manager.entity.PageObject;
import com.itszt.manager.exception.ServiceException;
import com.itszt.manager.service.OrderService;
import com.itszt.manager.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;

    @Override
    public PageObject<Order> findOrderList(Integer pageCurrent) {
        //1.参数校验
        if(pageCurrent==null||pageCurrent<1)
            //throw new IllegalArgumentException("页码不正确~~");
            pageCurrent=1;
        //2.查询总记录数并进行校验
        int rowCount=orderDao.getRowCount();
        if(rowCount==0)
            throw new ServiceException("记录不存在~~");
        //3.查询当前页要呈现的记录
        //3.1页面大小,例如每页最多显示3条
        int pageSize= PageUtil.getPageSize();
        //3.2当前页起始位置
        int startIndex=PageUtil.getStartIndex(pageCurrent);
        List<Order> records= orderDao.findAllOrderList(startIndex,pageSize);
        return PageUtil.newPageObject(pageCurrent,rowCount,pageSize,records);
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
