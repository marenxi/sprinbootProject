package com.itszt.manager.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    @GetMapping({"/home", "/", "/index"})
    public String home() {
        System.out.println("返回管理后台页面");
        return "/home/home";
    }
}
