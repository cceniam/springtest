package com.woniuxy.ackAndNackAndReject;

import com.rabbitmq.client.*;
import com.woniuxy.ConnectionUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class AboutAckConsumer2 {

    public static void main(String[] args) throws Exception{
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.basicConsume("aboutAckQueue",false,new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                System.out.println("AboutAckConsumer2 接收到一条消息: "+new String(body));

                //读取retryNum值,这里写在文件里,实际业务中一般放在数据库中
                Properties retryNumProperties = new Properties();
                retryNumProperties.load(new FileInputStream("Demos/RabbitMqDemo/src/main/resources/retryNum.properties"));
                String retryNumString = retryNumProperties.getProperty("retryNum");
                int retryNum = Integer.parseInt(retryNumString);

                //根据重试次数决定是否requeue
                channel.basicNack(envelope.getDeliveryTag(),false,retryNum<5);
                retryNum++;

                //写回retryNum值
                retryNumProperties.setProperty("retryNum",retryNum+"");
                retryNumProperties.store(new FileOutputStream("Demos/RabbitMqDemo/src/main/resources/retryNum.properties"),null);


            }

        });


    }

}
