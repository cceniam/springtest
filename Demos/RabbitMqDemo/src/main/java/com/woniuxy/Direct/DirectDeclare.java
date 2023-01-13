package com.woniuxy.Direct;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.woniuxy.ConnectionUtils;
import com.woniuxy.MqConst;

public class DirectDeclare {

    public static void main(String[] args) throws Exception {

        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        //创建交换机
        channel.exchangeDeclare(MqConst.DirectExchange, BuiltinExchangeType.DIRECT);

        //创建队列
        channel.queueDeclare(MqConst.QueryQueue,false,false,false,null);
        channel.queueDeclare(MqConst.AddDelUpdateQueue,false,false,false,null);

        //绑定交换和队列
        channel.queueBind(MqConst.QueryQueue,MqConst.DirectExchange,MqConst.Query);
        channel.queueBind(MqConst.AddDelUpdateQueue,MqConst.DirectExchange,MqConst.AddDelUpdate);

        //关闭资源
        channel.close();
        connection.close();

    }

}
