package com.woniuxy;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Component
public class DeadLetterQueueListener {

    @RabbitListener(queues = "deadLetterQueue")
    public void onMsg(Map<String,Object> map, Channel channel, Message message) throws IOException {

        System.out.println("DeadLetterQueueListener onMsg");
        System.out.println("currentTime: "+new Date());
        Set<String> keys = map.keySet();
        keys.forEach(key->{
            System.out.println(key);
            System.out.println(map.get(key));
        });

        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);

        //channel.basicReject();

    }


}
