package com.woniuxy.delayqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.woniuxy.ConnectionUtils;

import java.util.Date;

public class DelayProducer {

    public static void main(String[] args) throws Exception{

        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        String msg = "from DelayProducer ,time "+new Date();
        channel.basicPublish("delayExchange","",null,msg.getBytes());
        System.out.println(msg);

        channel.close();
        connection.close();

    }


}
