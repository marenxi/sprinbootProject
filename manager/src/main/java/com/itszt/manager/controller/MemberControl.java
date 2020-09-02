package com.itszt.manager.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.itszt.manager.entity.DataResponse;
import com.itszt.manager.entity.Member;
import com.itszt.manager.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MemberControl {
    @Autowired
    MemberService memberService;

    @GetMapping("/member")
    public String order() {
        System.out.println("返回会员管理页面");
        return "/member/member";
    }

    //更新时页面数据的回显
    @RequestMapping("/findMemberById")
    @ResponseBody
    public List<Member> findMemberById( Integer id){
        List<Member> list = memberService.findById(id);
        return list;

    }

    @RequestMapping("/MemberList")
    @ResponseBody
    public DataResponse findAllMember(){
        DataResponse dataResponse = new DataResponse();
        try {
            List<Member> memberList = memberService.findAll();
            System.out.println(memberList);
            System.out.println("返回会员集合数据");
            dataResponse.setCode(0);
            dataResponse.setData(memberList);
            dataResponse.setCount(memberList.size());
        }catch (Exception e){
            dataResponse.setCode(0);
            dataResponse.setData(null);
            dataResponse.setCount(0);
            System.out.println("e = " + e);
        }
        dataResponse.setMsg("");
        return dataResponse;

    }

    //根据会员id删除会员数据
    @RequestMapping(value="/memberDelete/{id}",method= RequestMethod.DELETE)
    public String deleteOrderById(@PathVariable Integer id){
        memberService.deleteMemberById(id);
        return "redirect:/member/MemberList";
    }

    //会员信息的新增
    @RequestMapping("/member/add")
    public ModelAndView memberAdd() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("member/memberAdd");
        return mv;
    }

    //会员信息的新增
    @PostMapping("/insertMember")
    @ResponseBody
    public String insertMember(Member member){
        memberService.insertMember(member);
        System.out.println("插入数据成功");
        return "/MemberList";
    }

    //1.1会员信息更新页面的跳转（需要根据id查询显示出对应的会员信息）
    @RequestMapping("/updateMember")
    public ModelAndView updateMemberById(Integer id,Model model){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("member/memberEdit");
        Member member = memberService.findMemberById(id);
        System.out.println(member);
        mv.addObject("member",member);
        //model.addAttribute("member1",member);
        return mv;
    }

    //1.2对会员信息进行更新并保存至数据库
    @RequestMapping("/doSaveMember")
    @ResponseBody
    public String doSaveMember(Member member,Model model){
        int rows = memberService.updateMemberById(member);
        System.out.println(rows);
        return "return:/MemberList";
    }


}
