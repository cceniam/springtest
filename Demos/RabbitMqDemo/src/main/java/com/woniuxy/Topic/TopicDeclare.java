package com.woniuxy.Topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.woniuxy.ConnectionUtils;
import com.woniuxy.MqConst;

public class TopicDeclare {

    public static void main(String[] args) throws Exception {

        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        //创建交换机
        channel.exchangeDeclare(MqConst.TopicExchange, BuiltinExchangeType.TOPIC);

        //创建队列
        channel.queueDeclare(MqConst.TopicQueue1,false,false,false,null);
        channel.queueDeclare(MqConst.TopicQueue2,false,false,false,null);

        //绑定交换和队列
        channel.queueBind(MqConst.TopicQueue1,MqConst.TopicExchange,MqConst.TopicQueue1Key);

        channel.queueBind(MqConst.TopicQueue2,MqConst.TopicExchange,MqConst.TopicQueue2Key1);
        channel.queueBind(MqConst.TopicQueue2,MqConst.TopicExchange,MqConst.TopicQueue2Key2);

        //关闭资源
        channel.close();
        connection.close();

    }

}
