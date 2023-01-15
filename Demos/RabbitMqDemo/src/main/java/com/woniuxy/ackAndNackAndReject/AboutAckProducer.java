package com.woniuxy.ackAndNackAndReject;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.woniuxy.ConnectionUtils;

import java.util.Date;

public class AboutAckProducer {

    public static void main(String[] args) throws Exception {

        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        //发送消息
        String msg = "我是一条来自 AboutAckProducer 的消息,sendTime: "+new Date();
        channel.basicPublish("aboutAckExchage","",null,msg.getBytes());
        System.out.println("AboutAckProducer sendMsg: "+msg);

        channel.close();
        connection.close();

    }



}
