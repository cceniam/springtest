package com.woniuxy.Simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.woniuxy.ConnectionUtils;
import com.woniuxy.MqConst;

import java.util.Date;

public class SimpleProducer {

    public static void main(String[] args) throws Exception {

        String msg = "我是一条来自 SimpleProducer 的消息, "+ new Date();


        Connection connection = ConnectionUtils.getConnection();


        //对接channel
        Channel channel = connection.createChannel();


        //发送消息
        //exchange如果不是用,需要传一个空字符串,不能传null
        channel.basicPublish("", MqConst.SIMPLE_QUEUE,null,msg.getBytes());
        System.out.println("send msg: "+msg);

        //关闭连接
        channel.close();
        connection.close();

    }


}
