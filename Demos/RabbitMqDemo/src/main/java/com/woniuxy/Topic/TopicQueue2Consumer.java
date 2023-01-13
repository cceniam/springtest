package com.woniuxy.Topic;

import com.rabbitmq.client.*;
import com.woniuxy.ConnectionUtils;
import com.woniuxy.MqConst;

import java.io.IOException;

public class TopicQueue2Consumer {

    public static void main(String[] args) throws Exception{


        //建立连接
        Connection connection = ConnectionUtils.getConnection();


        //对接channel
        Channel channel = connection.createChannel();


        //监听消息
        channel.basicConsume(MqConst.TopicQueue2,false,new DefaultConsumer(channel){  //手动动ack

            //具体的处理消息方法
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                String msg = new String(body);
                System.out.println("我是 TopicQueue2Consumer 接收到msg: "+msg);

                System.out.println("执行具体业务.......");

                //channel.basicAck(envelope.getDeliveryTag(), true); //multiple为true时,批量确认比当前deliveryTag小的所有消息
                channel.basicAck(envelope.getDeliveryTag(), false); //multiple为false时,只确认当前消息

            }
        });


    }

}
