package com.woniuxy.Fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.woniuxy.ConnectionUtils;
import com.woniuxy.MqConst;

import java.util.Date;

public class FanoutExchangeProducer {

    public static void main(String[] args) throws Exception {

        String msg = "我是一条来自 FanoutExchangeProducer 的消息, "+ new Date();


        Connection connection = ConnectionUtils.getConnection();


        //对接channel
        Channel channel = connection.createChannel();


        //发送消息
        channel.basicPublish(MqConst.FanoutExchange,"",null,msg.getBytes());
        System.out.println("send msg: "+msg);

        //关闭连接
        channel.close();
        connection.close();

    }


}
