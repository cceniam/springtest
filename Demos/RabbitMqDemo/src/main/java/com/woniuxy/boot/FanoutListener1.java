package com.woniuxy.boot;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class FanoutListener1 {

    @RabbitListener(queues = "fanout.A")
//    @RabbitHandler
    public void onMsg(Map<String,Object> map){
        System.out.println("FanoutListener1 onMsg");
        Set<String> keys = map.keySet();
        keys.forEach(key->{
            System.out.println(key);
            System.out.println(map.get(key));
        });
    }

}
