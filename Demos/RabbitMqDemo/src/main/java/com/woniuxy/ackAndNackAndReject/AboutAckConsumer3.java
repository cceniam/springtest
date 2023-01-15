package com.woniuxy.ackAndNackAndReject;

import com.rabbitmq.client.*;
import com.woniuxy.ConnectionUtils;

import java.io.IOException;

public class AboutAckConsumer3 {

    private static int num=0;

    public static void main(String[] args) throws Exception{
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.basicConsume("aboutAckQueue",false,new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                System.out.println("AboutAckConsumer3 接收到一条消息: "+new String(body));

                if(num>2){
                    channel.basicAck(envelope.getDeliveryTag(),false);    //正常Ack,消息被消费掉,将从queue中移除
                }else {
                    channel.basicNack(envelope.getDeliveryTag(),false,true);
                    num++;
                }


            }

        });


    }

}
