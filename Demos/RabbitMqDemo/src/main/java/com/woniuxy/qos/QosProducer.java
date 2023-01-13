package com.woniuxy.qos;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.woniuxy.ConnectionUtils;

public class QosProducer {

    public static void main(String[] args) throws Exception{

        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        for (int i=1;i<1000;i++){
            channel.basicPublish("qosExchange","",null,(i+" QosProducer ").getBytes());
        }

        channel.close();
        connection.close();

    }


}
