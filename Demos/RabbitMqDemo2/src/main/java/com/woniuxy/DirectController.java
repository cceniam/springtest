package com.woniuxy;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class DirectController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping("/send")
    @ResponseBody
    public String send(String exchange, String routingKey, String name){

        //准备发送的数据
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        map.put("name",name);

        rabbitTemplate.convertAndSend(exchange,routingKey,map);

        return "ok";
    }


    @RequestMapping("/deadsend")
    @ResponseBody
    public String deadsend(String exchange, String routingKey, String deadMsg){

        //准备发送的数据
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        map.put("deadMsg",deadMsg);

        rabbitTemplate.convertAndSend(exchange,routingKey,map);

        return "ok";
    }


    @RequestMapping("/")
    @ResponseBody
    public String root(){
        return "ok";
    }



}
