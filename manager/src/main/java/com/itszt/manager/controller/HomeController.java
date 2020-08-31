package com.itszt.manager.controller;

import com.itszt.manager.entity.DataResponse;
import com.itszt.manager.entity.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/{page}")
    public String toPage(@PathVariable String page){
        return "page";
    }
}
