package com.woniuxy;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

@Component
public class DirectListener1 {

    @RabbitListener(queues = "TestDirectQueue1")
//    @RabbitHandler
    public void onMsg(Map<String,Object> map, Channel channel, Message message) throws IOException {

        //自动ack的时候,如果方法正常执行,就确认消息被消费
        //如果方法执行失败,则自动重试
        try {
            int a = 3 / 0;
            System.out.println("执行业务逻辑......");

            System.out.println("DirectListener1 onMsg");
            Set<String> keys = map.keySet();
            keys.forEach(key->{
                System.out.println(key);
                System.out.println(map.get(key));
            });

            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }catch (Exception e){
            //fixed 没有拒绝消息的效果
            //参考 ack补充专题
            e.printStackTrace();
            //log.....
            //自动ack的时候,监听方法里不要抛出异常,否则会无限重试,导致死循环
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
//            channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);
        }



    }

}
