package com.woniuxy.ackAndNackAndReject;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.woniuxy.ConnectionUtils;

public class AboutAckDeclare {

    public static void main(String[] args) throws Exception {

        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        //声明交换机,队列,并进行绑定
        channel.exchangeDeclare("aboutAckExchage", BuiltinExchangeType.FANOUT);
        channel.queueDeclare("aboutAckQueue",false,false,false,null);
        channel.queueBind("aboutAckQueue","aboutAckExchage","");

        channel.close();
        connection.close();

    }


}
