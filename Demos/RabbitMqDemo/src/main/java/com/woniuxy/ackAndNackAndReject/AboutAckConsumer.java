package com.woniuxy.ackAndNackAndReject;

import com.rabbitmq.client.*;
import com.woniuxy.ConnectionUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class AboutAckConsumer {

    public static void main(String[] args) throws Exception{
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.basicConsume("aboutAckQueue",false,new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                System.out.println("AboutAckConsumer 接收到一条消息: "+new String(body));

//                channel.basicAck(envelope.getDeliveryTag(),false);    //正常Ack,消息被消费掉,将从queue中移除

                //Nack 否定ack ,同时这里指定了requeue为true
                //此时RabbitMQ就会找其它消费者来处理消息
                //如果消费者只有一个,那就会导致无限循环
//                channel.basicNack(envelope.getDeliveryTag(),false,true);

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

                /*
                 * Nack与Reject:
                 *  Nack可以通过指定multiple为true来批量否定ack
                 *  Reject只能单条否定ack, 等效于 channel.basicNack(envelope.getDeliveryTag(),false,true);
                 */
                //channel.basicReject(envelope.getDeliveryTag(),true);


                //复制出来两个Consumer: AboutAckConsumer2 AboutAckConsumer3
                //结合Debug可以看到RabbitMQ尝试调用不同的Consumer 直到实现消息的正常Ack为止

            }

        });


    }

}
