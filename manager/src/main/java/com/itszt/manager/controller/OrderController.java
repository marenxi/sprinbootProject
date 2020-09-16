package com.itszt.manager.controller;

import com.itszt.manager.entity.DataResponse;
import com.itszt.manager.entity.Order;
import com.itszt.manager.service.OrderService;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.Request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping
    public String order() {
        System.out.println("返回订单管理页面");
        return "/order/order";
    }

    //查询出所有订单
    @GetMapping("/list")
    @ResponseBody
    public DataResponse orderList() {
        DataResponse dataResponse = new DataResponse();
        try {
            List<Order> orderList = orderService.findOrderList();
            System.out.println("返回订单集合数据:");
            System.out.println(orderList);
            dataResponse.setCode(0);
            dataResponse.setData(orderList);
            dataResponse.setCount(orderList.size());
            dataResponse.setMsg("查询订单数据成功");
        }catch (Exception e){
            dataResponse.setMsg("");
            dataResponse.setCode(0);
            dataResponse.setData(null);
            dataResponse.setCount(0);

        }
        return dataResponse;
    }

    @PostMapping("/del")
    @ResponseBody
    public DataResponse orderDel(String orderCode) {
        DataResponse dataResponse = new DataResponse();
        System.out.println("删除订单" + orderCode);
        dataResponse.setMsg("删除成功");
        dataResponse.setCode(200);
        return dataResponse;
    }

    @RequestMapping("/edit")
    public ModelAndView  orderEdit(String orderCode) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("order/orderEdit");
        return mv;
    }

    @RequestMapping("/add")
    public ModelAndView  orderAdd(String orderCode) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("order/orderAdd");
        return mv;
    }

    @RequestMapping("/insertOrder")
    @ResponseBody
    public DataResponse insertOrder(Order order){
        DataResponse response = new DataResponse();
        int rows=orderService.insertOrder(order);
        System.out.println(rows);
        response.setMsg("新增订单信息成功");
        return response;
    }

    /*根据时间段和输入框字段进行查询*/
    @GetMapping(value = "findOrderByConditions")
    @ResponseBody
    public DataResponse findOrderByConditions(String startTime,String endTime, String orderId,
                                              String talentName, String workMenuId, String workType ){
        DataResponse response=new DataResponse();
        List<Order> orderList = orderService.findOrderByConditions(startTime,endTime,
                                                                    orderId, talentName,
                                                                     workMenuId, workType);
        response.setData(orderList);
        response.setCount(orderList.size());
        response.setMsg("查询数据成功");
        return  response;

    }
    /*根据订单的状态查询订单信息*/
    @RequestMapping("findOrderByStatus")
    @ResponseBody
        public DataResponse findOrderByStatus(String orderStatus){
        DataResponse response=new DataResponse();
        List<Order> orderList = orderService.findOrderByOrderStatus(orderStatus);
        response.setData(orderList);
        response.setCount(orderList.size());
        response.setMsg("查询数据成功");
        return  response;
    }

}
