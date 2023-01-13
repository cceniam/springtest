package com.woniuxy;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootTest
public class RabbitMqMainTest {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    void testSendSimple(){
        for (int i = 0; i < 10; i++) {
            String msg = "simpleMsg "+new Date();
            rabbitTemplate.convertAndSend("simpleQueue",msg);
        }

    }

    @Test
    void testConsumerSimple(){
        Object msg = rabbitTemplate.receiveAndConvert("simpleQueue");
        System.out.println(msg);
    }

    @Test
    void testDirectSend(){

        //准备发送的数据
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);

        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting1", map);


        //准备发送的数据
        String messageId2 = String.valueOf(UUID.randomUUID());
        String messageData2 = "test message, hello!";
        String createTime2 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map2 = new HashMap<>();
        map2.put("messageId", messageId2);
        map2.put("messageData", messageData2);
        map2.put("createTime", createTime2);

        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting2", map2);


    }



}
