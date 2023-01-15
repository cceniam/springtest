package com.woniuxy.ackAndNackAndReject;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.woniuxy.ConnectionUtils;

import java.util.HashMap;
import java.util.Map;

public class AboutAckDeclare {

    public static void main(String[] args) throws Exception {

        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        //否定Ack并且requeue为false的消息会变成死信,
        // 如果没有配置死信交换机和死信队列则丢弃,
        // 如果配置了则由死信交换机和死信队列处理死信消息.

        //死信部分
        channel.exchangeDeclare("deadExchange", BuiltinExchangeType.FANOUT);
        channel.queueDeclare("deadQueue",false,false,false,null);
        channel.queueBind("deadQueue","deadExchange","");

        //声明交换机,队列,并进行绑定
        channel.exchangeDeclare("aboutAckExchage", BuiltinExchangeType.FANOUT);
//        channel.queueDeclare("aboutAckQueue",false,false,false,null);

        Map<String,Object> arguments = new HashMap<>();
        //这些key值 是固定的
        arguments.put("x-dead-letter-exchange","deadExchange");
        arguments.put("x-dead-letter-routing-key","");
        channel.queueDeclare("aboutAckQueue",false,false,false,arguments);

        channel.queueBind("aboutAckQueue","aboutAckExchage","");

        channel.close();
        connection.close();

    }


}
