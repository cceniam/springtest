package com.woniuxy.Simple;

import com.rabbitmq.client.*;

import java.io.IOException;

public class SimpleConsumer {

    public static void main(String[] args) throws Exception{


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


        //监听消息
//        channel.basicConsume("simple_queue",new DefaultConsumer(channel){   //没有使用ack
        channel.basicConsume("simple_queue",true,new DefaultConsumer(channel){  //自动ack

            //具体的处理消息方法
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //super.handleDelivery(consumerTag, envelope, properties, body);

                System.out.println(consumerTag);
                System.out.println(envelope);
                System.out.println(properties);

                String msg = new String(body);
                System.out.println("我是 SimpleConsumer 接收到msg: "+msg);

            }
        });


    }

}
