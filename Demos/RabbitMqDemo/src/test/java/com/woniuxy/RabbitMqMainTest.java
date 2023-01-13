package com.woniuxy;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

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


}
