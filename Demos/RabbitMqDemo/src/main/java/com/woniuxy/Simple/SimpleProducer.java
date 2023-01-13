package com.woniuxy.Simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Date;

public class SimpleProducer {

    public static void main(String[] args) throws Exception {

        String msg = "我是一条来自 SimpleProducer 的消息, "+ new Date();


        //建立连接

        //初始化连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");

        //建立一个与RabbitMQ之间的连接
        Connection connection = connectionFactory.newConnection();


        //对接channel
        Channel channel = connection.createChannel();


        //发送消息
        //exchange如果不是用,需要传一个空字符串,不能传null
        channel.basicPublish("","simple_queue",null,msg.getBytes());
        System.out.println("send msg: "+msg);

        //关闭连接
        channel.close();
        connection.close();

    }


}
