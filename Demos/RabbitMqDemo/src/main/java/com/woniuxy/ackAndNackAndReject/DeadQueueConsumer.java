package com.woniuxy.ackAndNackAndReject;

import com.rabbitmq.client.*;
import com.woniuxy.ConnectionUtils;

import java.io.IOException;

public class DeadQueueConsumer {


    public static void main(String[] args) throws Exception{
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.basicConsume("deadQueue",false,new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                System.out.println("DeadQueueConsumer 接收到一条消息: "+new String(body));

                System.out.println("针对多次重试仍失败的消息的业务处理逻辑, 可以是人工处理等.......");

                channel.basicAck(envelope.getDeliveryTag(),false);
            }

        });


    }

}
