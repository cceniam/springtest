package com.woniuxy.Simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 去RabbitMQ创建交换机或者队列
 */
public class SimpleDeclare {

    public static void main(String[] args) throws Exception {
        //初始化连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");

        //建立一个与RabbitMQ之间的连接
        Connection connection = connectionFactory.newConnection();

        //获取channel (具体业务对接人)
        Channel channel = connection.createChannel();

        //创建队列
        channel.queueDeclare("simple_queue",false,false,false,null);

        //如果再次创建同名的队列或者交换机,如果具体配置信息和原有的不一致会导致报错
//        channel.queueDeclare("simple_queue",true,false,false,null);

        channel.close();
        connection.close();

    }


}
