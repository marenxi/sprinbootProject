package com.itszt.manager.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.itszt.manager.entity.DataResponse;
import com.itszt.manager.entity.Member;
import com.itszt.manager.service.MemberService;
import com.itszt.manager.util.ObjectMapperUtil;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class MemberControl {
    @Autowired
    MemberService memberService;
    @Autowired
    RedisTemplate redisTemplate;
    Logger logger=LoggerFactory.getLogger(MemberControl.class);
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
            System.out.println("返回会员集合数据:"+memberList);
            redisTemplate.opsForValue().set(memberList,memberList);
            System.out.println(memberList);
            logger.info("从redis缓存中取，会员列表为{}",redisTemplate.opsForValue().get(memberList));
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


    //根据时间段和任意会员的字段进行会员信息的精准查询
    @RequestMapping("/doMemberListBy")
    @ResponseBody
    public DataResponse fingMembersByManyConditions(String startDate,String endDate,String name,String workType,String telephone,Integer age,Integer page, Integer limit){
        DataResponse dataResponse = new DataResponse();
        Integer pageStart = (page - 1) * limit;
        Integer pageEnd = page * limit-1;
        logger.info(String.valueOf(pageStart));
        logger.info(String.valueOf(pageEnd));
        try {
            List<Member> list = new ArrayList<Member>();
//            Object memberList1 = redisTemplate.opsForValue().get("memberList");
//            if(memberList1!=null){redisTemplate.delete("memberList");}
//            logger.info("从redis缓存中取，会员列表为{}",memberList1);
            Integer count = memberService.countManyConditions(startDate, endDate, name, workType, telephone, age, pageStart, pageEnd);
//            if(memberList1==null) {/*如果缓存中为空则去数据库中查询取值，取出之后再放入到缓存中去*/
                List<Member> memberList = memberService.findByManyConditions(startDate, endDate, name, workType, telephone, age, pageStart, pageEnd);
                String JsonResult = ObjectMapperUtil.toJSON(memberList);//将对象转化为JSON
                redisTemplate.opsForValue().set("memberList", JsonResult);
                logger.info("从数据库中获得的查询结果为：{}",memberList);
                dataResponse.setCode(0);
                dataResponse.setData(memberList);
                dataResponse.setCount(count);
                dataResponse.setMsg("");
                return dataResponse;
//            }else{
//                logger.info("从缓存中获得的查询结果为：{}",memberList1);
//                List memberList = ObjectMapperUtil.toObject(memberList1.toString(), list.getClass());
//                dataResponse.setCode(0);
//                dataResponse.setData(memberList);
//                dataResponse.setCount(count);
//                dataResponse.setMsg("");
//                return dataResponse;
//            }
        }catch (Exception e){
            dataResponse.setCode(0);
            dataResponse.setData(null);
            dataResponse.setCount(0);
            System.out.println("e = " + e);
            dataResponse.setMsg("您查找的会员信息不存在，请重新输入查询条件");
            dataResponse.setMsg("");
            return dataResponse;

        }

    }


}
