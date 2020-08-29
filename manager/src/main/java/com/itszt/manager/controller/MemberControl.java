package com.itszt.manager.controller;

import com.itszt.manager.entity.Member;
import com.itszt.manager.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MemberControl {
    @Autowired
    MemberService memberService;


    @RequestMapping("/findMember")
    @ResponseBody
    public List<Member> findMember( Integer id, Model model){
        List<Member> list = memberService.findById(id);
        //model.addAttribute("list", list);
        System.out.println(list);
        return list;
    }


}
