package com.woniuxy.delayqueue;

import com.rabbitmq.client.*;
import com.woniuxy.ConnectionUtils;

import java.io.IOException;
import java.util.Date;

public class DeadLetterConsumer {

    public static void main(String[] args) throws Exception {

        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();


        //声明死信交换机
        channel.exchangeDeclare("deadLetterExchange", BuiltinExchangeType.FANOUT);

        //声明一个队列,用于绑定到死信交换机
        channel.queueDeclare("deadLetterQueue",true,false,false,null);

        channel.queueBind("deadLetterQueue","deadLetterExchange","");

        channel.basicConsume("deadLetterQueue",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println("我是 DeadLetterConsumer 接收到msg: "+msg);
                System.out.println(new Date());

                System.out.println("执行具体业务.......");
            }
        });

    }


}
