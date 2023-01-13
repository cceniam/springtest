package com.woniuxy.tx;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.woniuxy.ConnectionUtils;
import com.woniuxy.MqConst;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

//复用的DirectExchangeProducer  启动本类前先启动 DirectXXXXConsumer
public class ConfirmProducer {

    public static void main(String[] args) throws Exception {

        String msgQuery = "我是一条来自 DirectExchangeProducer 的 Query 消息, "+ new Date();
        String msgAddDelUpdate = "我是一条来自 DirectExchangeProducer 的 AddDelUpdate 消息, "+ new Date();


        Connection connection = ConnectionUtils.getConnection();


        //对接channel
        Channel channel = connection.createChannel();


        //发送消息  使用confirm机制
        channel.confirmSelect();

        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("-------handleAck----------");
                System.out.println(deliveryTag);
                System.out.println(multiple);
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("-------handleNack----------");
                System.out.println(deliveryTag);
                System.out.println(multiple);
            }
        });

        //发送Query消息
        //第三个参数指定为MessageProperties.PERSISTENT_TEXT_PLAIN 可以做到消息持久化
        channel.basicPublish(MqConst.DirectExchange,MqConst.Query, MessageProperties.PERSISTENT_TEXT_PLAIN,msgQuery.getBytes());
        System.out.println("send msg: "+msgQuery);

        TimeUnit.SECONDS.sleep(10);

        //发送AddDelUpdate消息
        channel.basicPublish(MqConst.DirectExchange,MqConst.AddDelUpdate,null,msgAddDelUpdate.getBytes());
        System.out.println("send msg: "+msgAddDelUpdate);

        TimeUnit.SECONDS.sleep(5);

        //关闭连接
        channel.close();
        connection.close();

    }


}
