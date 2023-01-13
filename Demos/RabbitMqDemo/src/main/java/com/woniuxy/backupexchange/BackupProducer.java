package com.woniuxy.backupexchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.woniuxy.ConnectionUtils;

public class BackupProducer {

    public static void main(String[] args) throws Exception{

        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.basicPublish("normalExchange","test",null,"backupTest".getBytes());

        channel.basicPublish("normalExchange","testError",null,"backupTestError".getBytes());


        channel.close();
        connection.close();

    }


}
