package com.woniuxy.boot;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "simpleQueue")
public class SimpleListener2 {

    @RabbitHandler
    public void hadleMsg(String msg){
        System.out.println("SimpleListener2 接收到消息: "+msg);
    }

}
