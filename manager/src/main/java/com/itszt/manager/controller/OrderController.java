package com.itszt.manager.controller;

import com.itszt.manager.entity.DataResponse;
import com.itszt.manager.entity.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping("/order")
public class OrderController {

    @GetMapping
    public String order() {
        System.out.println("返回订单管理页面");
        return "/order/order";
    }

    @GetMapping("/list")
    @ResponseBody
    public DataResponse orderList() {
        DataResponse dataResponse = new DataResponse();
        ArrayList<Order> orders = new ArrayList<>();
        Order order = new Order();
        order.setOrderCode("x1234");
        order.setCustmerId("1234");
        orders.add(order);
        System.out.println("返回订单集合数据");
        dataResponse.setCode(0);
        dataResponse.setData(orders);
        dataResponse.setCount(orders.size());
        dataResponse.setMsg("");
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
}
