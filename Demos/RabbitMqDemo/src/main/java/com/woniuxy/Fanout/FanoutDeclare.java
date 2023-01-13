package com.woniuxy.Fanout;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.woniuxy.ConnectionUtils;
import com.woniuxy.MqConst;

public class FanoutDeclare {

    public static void main(String[] args) throws Exception {

        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        //创建交换机
//        channel.exchangeDeclare(MqConst.FanoutExchange,"fanout");
        channel.exchangeDeclare(MqConst.FanoutExchange, BuiltinExchangeType.FANOUT);

        //创建队列
        channel.queueDeclare(MqConst.FanoutQueue1,false,false,false,null);
        channel.queueDeclare(MqConst.FanoutQueue2,false,false,false,null);

        //绑定交换和队列
        channel.queueBind(MqConst.FanoutQueue1,MqConst.FanoutExchange,"");
        channel.queueBind(MqConst.FanoutQueue2,MqConst.FanoutExchange,"xx");

        //关闭资源
        channel.close();
        connection.close();

    }

}
