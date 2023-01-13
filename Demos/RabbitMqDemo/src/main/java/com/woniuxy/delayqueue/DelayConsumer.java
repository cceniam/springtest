package com.woniuxy.delayqueue;

import com.rabbitmq.client.*;
import com.woniuxy.ConnectionUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DelayConsumer {

    public static void main(String[] args) throws Exception {

        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();


        //声明交换机
        channel.exchangeDeclare("delayExchange", BuiltinExchangeType.FANOUT);

        //声明一个队列,用于绑定到交换机
        //--------注意,该队列不要有消费者
        Map<String,Object> arguments = new HashMap<>();
        //这些key值 是固定的
        arguments.put("x-message-ttl",20000);  //20s后过期
        arguments.put("x-dead-letter-exchange","deadLetterExchange");
        arguments.put("x-dead-letter-routing-key","");
        channel.queueDeclare("delayQueue",true,false,false,arguments);

        channel.queueBind("delayQueue","delayExchange","");

    }


}
