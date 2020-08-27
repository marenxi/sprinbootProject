package com.itszt.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

}
