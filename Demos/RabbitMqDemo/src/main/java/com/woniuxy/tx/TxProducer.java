package com.woniuxy.tx;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.woniuxy.ConnectionUtils;
import com.woniuxy.MqConst;

import java.util.Date;

//复用的DirectExchangeProducer  启动本类前先启动 DirectXXXXConsumer
public class TxProducer {

    public static void main(String[] args) throws Exception {

        String msgQuery = "我是一条来自 DirectExchangeProducer 的 Query 消息, "+ new Date();
        String msgAddDelUpdate = "我是一条来自 DirectExchangeProducer 的 AddDelUpdate 消息, "+ new Date();


        Connection connection = ConnectionUtils.getConnection();


        //对接channel
        Channel channel = connection.createChannel();


        //发送消息

        try{
            //开启事务
            channel.txSelect();

            //发送Query消息
            //第三个参数指定为MessageProperties.PERSISTENT_TEXT_PLAIN 可以做到消息持久化
            channel.basicPublish(MqConst.DirectExchange,MqConst.Query, MessageProperties.PERSISTENT_TEXT_PLAIN,msgQuery.getBytes());
            System.out.println("send msg: "+msgQuery);

            int a = 3/0;

            //发送AddDelUpdate消息
            channel.basicPublish(MqConst.DirectExchange,MqConst.AddDelUpdate,null,msgAddDelUpdate.getBytes());
            System.out.println("send msg: "+msgAddDelUpdate);

            //提交事务
            channel.txCommit();

        }catch (Exception e){
            e.printStackTrace();
            channel.txRollback();
        }




        //关闭连接
        channel.close();
        connection.close();

    }


}
