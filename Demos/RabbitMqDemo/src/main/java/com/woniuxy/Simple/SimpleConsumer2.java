package com.woniuxy.Simple;

import com.rabbitmq.client.*;

import java.io.IOException;

public class SimpleConsumer2 {

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
//        channel.basicConsume("simple_queue",true,new DefaultConsumer(channel){  //自动ack
        channel.basicConsume("simple_queue",false,new DefaultConsumer(channel){  //手动动ack

            //具体的处理消息方法
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //super.handleDelivery(consumerTag, envelope, properties, body);

                System.out.println(consumerTag);
                System.out.println(envelope);
                System.out.println(properties);

                String msg = new String(body);
                System.out.println("我是 SimpleConsumer22222 接收到msg: "+msg);

                System.out.println("执行具体业务.......");

                //channel.basicAck(envelope.getDeliveryTag(), true); //multiple为true时,批量确认比当前deliveryTag小的所有消息
                channel.basicAck(envelope.getDeliveryTag(), false); //multiple为false时,只确认当前消息

            }
        });


    }

}
