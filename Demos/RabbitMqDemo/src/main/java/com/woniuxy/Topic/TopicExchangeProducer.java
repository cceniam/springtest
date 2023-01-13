package com.woniuxy.Topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.woniuxy.ConnectionUtils;
import com.woniuxy.MqConst;

import java.util.Date;

public class TopicExchangeProducer {

    public static void main(String[] args) throws Exception {

        Connection connection = ConnectionUtils.getConnection();

        //对接channel
        Channel channel = connection.createChannel();

        sendMsg(channel,"quick.orange.rabbit");
        sendMsg(channel,"lazy.orange.elephant");
        sendMsg(channel,"quick.orange.fox");
        sendMsg(channel,"lazy.pink.rabbit");
        sendMsg(channel,"quick.brown.fox");

        //关闭连接
        channel.close();
        connection.close();

    }


    //发送消息
    private static void sendMsg(Channel channel,String routingKey) throws Exception{
        String msg = routingKey+" "+new Date();
        System.out.println("TopicExchangeProducer sendMsg: "+msg);
        channel.basicPublish(MqConst.TopicExchange,routingKey,null,msg.getBytes());
    }


}
