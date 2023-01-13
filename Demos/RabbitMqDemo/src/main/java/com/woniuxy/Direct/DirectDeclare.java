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

        //durable参数指定是否持久化,指定了持久化之后,即便软件重启也会自动恢复

        //创建交换机
        channel.exchangeDeclare(MqConst.DirectExchange, BuiltinExchangeType.DIRECT,true);

        //创建队列
        channel.queueDeclare(MqConst.QueryQueue,true,false,false,null);
        channel.queueDeclare(MqConst.AddDelUpdateQueue,true,false,false,null);

        //绑定交换和队列
        channel.queueBind(MqConst.QueryQueue,MqConst.DirectExchange,MqConst.Query);
        channel.queueBind(MqConst.AddDelUpdateQueue,MqConst.DirectExchange,MqConst.AddDelUpdate);

        //关闭资源
        channel.close();
        connection.close();

    }

}
