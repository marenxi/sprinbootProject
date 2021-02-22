package com.itszt.manager.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itszt.manager.entity.Member;
import com.itszt.manager.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "TestDirectQueue")//监听的队列名称 TestDirectQueue
public class DirectReceiver {
    public static final Logger logger=LoggerFactory.getLogger(DirectReceiver.class);
    @Autowired
    private MemberService memberService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @RabbitHandler
    public void process(Member message) throws JsonProcessingException {
        logger.info("DirectReceiver消费者收到消息  : " + message.toString());
        memberService.insertMember(message);
        throw new RuntimeException();

    }

}