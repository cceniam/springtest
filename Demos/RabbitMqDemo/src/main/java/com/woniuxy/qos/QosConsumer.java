package com.woniuxy.qos;

import com.rabbitmq.client.*;
import com.woniuxy.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class QosConsumer {

    public static void main(String[] args) throws Exception {

        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();


        //声明交换机,作为备用交换机
        channel.exchangeDeclare("qosExchange", BuiltinExchangeType.FANOUT);

        //声明一个队列,用于绑定到备用交换机
        channel.queueDeclare("qosQueue",true,false,false,null);

        //设置限流
        channel.basicQos(20);

        channel.queueBind("qosQueue","qosExchange","");

        channel.basicConsume("qosQueue",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println("我是 QosConsumer 接收到msg: "+msg);

                System.out.println("执行具体业务.......");

                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


                channel.basicAck(envelope.getDeliveryTag(),false);

            }
        });

    }


}
