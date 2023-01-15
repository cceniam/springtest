package com.woniuxy.ackAndNackAndReject;

import com.rabbitmq.client.*;
import com.woniuxy.ConnectionUtils;

import java.io.IOException;

public class AboutAckConsumer {

    public static void main(String[] args) throws Exception{
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.basicConsume("aboutAckQueue",false,new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                System.out.println("AboutAckConsumer 接收到一条消息: "+new String(body));

                channel.basicAck(envelope.getDeliveryTag(),false);
            }

        });


    }

}
