package com.woniuxy.boot;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicListener2 {

    @RabbitListener(queues = "topic.woman")
//    @RabbitHandler
    public void onMsg(String msg){
        System.out.println("TopicListener2 onMsg: "+msg);

    }

}
