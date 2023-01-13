package com.woniuxy.returnlistener;

import com.rabbitmq.client.*;
import com.woniuxy.ConnectionUtils;
import com.woniuxy.MqConst;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

//需要在发消息的时候把第三个参数设置为true，同时定义一个returnListener
public class ReturnListenerProducer {

    public static void main(String[] args) throws Exception {

        String msgQuery = "我是一条来自 DirectExchangeProducer 的 Query 消息, "+ new Date();
        String msgAddDelUpdate = "我是一条来自 DirectExchangeProducer 的 AddDelUpdate 消息, "+ new Date();


        Connection connection = ConnectionUtils.getConnection();


        //对接channel
        Channel channel = connection.createChannel();


        //添加returnListener
        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(replyCode);
                System.out.println(replyText);
                System.out.println(exchange);
                System.out.println(routingKey);
                System.out.println(properties);
                System.out.println(new String(body));

            }
        });

        //发送消息

        //发送Query消息
        //第三个参数指定为MessageProperties.PERSISTENT_TEXT_PLAIN 可以做到消息持久化
        channel.basicPublish(MqConst.DirectExchange,MqConst.Query+"111",true, MessageProperties.PERSISTENT_TEXT_PLAIN,msgQuery.getBytes());
        System.out.println("send msg: "+msgQuery);

        TimeUnit.SECONDS.sleep(10);

        //发送AddDelUpdate消息
        channel.basicPublish(MqConst.DirectExchange,MqConst.AddDelUpdate+"111",null,msgAddDelUpdate.getBytes());
        System.out.println("send msg: "+msgAddDelUpdate);

        TimeUnit.SECONDS.sleep(5);

        //关闭连接
        channel.close();
        connection.close();

    }


}
