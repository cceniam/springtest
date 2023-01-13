package com.woniuxy.Direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.woniuxy.ConnectionUtils;
import com.woniuxy.MqConst;

import java.util.Date;

public class DirectExchangeProducer {

    public static void main(String[] args) throws Exception {

        String msgQuery = "我是一条来自 DirectExchangeProducer 的 Query 消息, "+ new Date();
        String msgAddDelUpdate = "我是一条来自 DirectExchangeProducer 的 AddDelUpdate 消息, "+ new Date();


        Connection connection = ConnectionUtils.getConnection();


        //对接channel
        Channel channel = connection.createChannel();


        //发送消息

        //发送Query消息
        channel.basicPublish(MqConst.DirectExchange,MqConst.Query,null,msgQuery.getBytes());
        System.out.println("send msg: "+msgQuery);

        //发送AddDelUpdate消息
        channel.basicPublish(MqConst.DirectExchange,MqConst.AddDelUpdate,null,msgAddDelUpdate.getBytes());
        System.out.println("send msg: "+msgAddDelUpdate);



        //关闭连接
        channel.close();
        connection.close();

    }


}
