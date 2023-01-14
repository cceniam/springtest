package com.woniuxy;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class DirectListener2 {

    @RabbitListener(queues = "TestDirectQueue2")
//    @RabbitHandler
    public void onMsg(Map<String,Object> map){
        System.out.println("DirectListener2 onMsg");
        Set<String> keys = map.keySet();
        keys.forEach(key->{
            System.out.println(key);
            System.out.println(map.get(key));
        });
    }

}
