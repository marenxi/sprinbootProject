package com.itszt.manager.controller;


import com.itszt.manager.entity.Member;
import com.itszt.manager.service.MemberService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author : JCccc
 * @CreateTime : 2019/9/3
 * @Description :
 **/
@RestController
public class SendMessageController {
    @Autowired
    private MemberService memberService;

    @Autowired
    RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法
    /*demoController*/
    @GetMapping("/sendDirectMessage")
    public String sendDirectMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", map);
        return "ok";
    }

    /*业务Controller需求1：查询到一条记录的同时则新增一条记录（生产者也是消费者）*/
    //更新时页面数据的回显
    @RequestMapping("/findMemberBy")
    @ResponseBody
    public List<Member> findMemberById(Integer id){
        List<Member> list = memberService.findById(id);
        if(list!=null&&list.size()>0){
            Member member = new Member();
            member.setName("小小");
            member.setId(2021001);
            member.setWorkType("技工");
            member.setAge(16);
            rabbitTemplate.convertAndSend(member);
        }
        return list;

    }


}
