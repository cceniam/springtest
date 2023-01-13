package com.woniuxy.backupexchange;

import com.rabbitmq.client.*;
import com.woniuxy.ConnectionUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NormalExchaveConsumer {

    public static void main(String[] args) throws Exception {

        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();



        //声明目标交换机,并配置其备用交换机
        //键值是固定的 代表备用交换机
        Map<String,Object> map = new HashMap<>();
        map.put("alternate-exchange","backupExchange");
        channel.exchangeDeclare("normalExchange", BuiltinExchangeType.DIRECT,true,false,map);


        //声明一个队列,用于绑定到目标交换机
        channel.queueDeclare("normalQueue",true,false,false,null);

        channel.queueBind("normalQueue","normalExchange","test");

        channel.basicConsume("normalQueue",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println("我是 NormalExchaveConsumer 接收到msg: "+msg);

                System.out.println("执行具体业务.......");
            }
        });

    }


}
