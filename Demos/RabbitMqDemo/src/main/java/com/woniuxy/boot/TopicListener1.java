package com.woniuxy.boot;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class TopicListener1 {

    @RabbitListener(queues = "topic.man")
//    @RabbitHandler
    public void onMsg(String msg){
        System.out.println("TopicListener1 onMsg: "+msg);

    }

}
