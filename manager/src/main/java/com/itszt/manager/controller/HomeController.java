package com.itszt.manager.controller;

import com.itszt.manager.entity.DataResponse;
import com.itszt.manager.entity.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class HomeController {
    @GetMapping("/")
    public String defaultPage() {
        System.out.println("访问http://localhost:8080/返回index页面");
        return "index";
    }

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        System.out.println("访问http://localhost:8080/index返回index页面");
        mv.setViewName("/index");
        return mv;
    }

    @GetMapping({"/home", "/home1"})
    public String home() {
        System.out.println("返回管理后台页面");
        return "home";
    }

    @GetMapping("/order")
    public String order() {
        System.out.println("返回订单管理页面");
        return "order";
    }

    @GetMapping("/orderList")
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
}
