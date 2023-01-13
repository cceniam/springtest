package com.woniuxy.backupexchange;

import com.rabbitmq.client.*;
import com.woniuxy.ConnectionUtils;

import java.io.IOException;

public class BackupExchaveConsumer {

    public static void main(String[] args) throws Exception {

        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();


        //声明交换机,作为备用交换机
        channel.exchangeDeclare("backupExchange", BuiltinExchangeType.FANOUT);

        //声明一个队列,用于绑定到备用交换机
        channel.queueDeclare("backupQueue",true,false,false,null);

        channel.queueBind("backupQueue","backupExchange","");

        channel.basicConsume("backupQueue",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println("我是 BackupExchaveConsumer 接收到msg: "+msg);

                System.out.println("执行具体业务.......");
            }
        });

    }


}
