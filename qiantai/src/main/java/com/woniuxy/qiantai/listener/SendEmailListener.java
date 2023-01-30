package com.woniuxy.qiantai.listener;

import com.google.code.kaptcha.Producer;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class SendEmailListener {

    @Autowired
    Producer producer;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @RabbitListener(queues = "sendEmailQueue")
    public void onMsg(String email, Channel channel, Message message) throws IOException {
        System.out.println("向邮箱发送邮件 "+email);

        //发送验证码邮件
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("woniumrwang@qq.com");
        mailMessage.setTo(email);  //用自己的邮箱做测试
        mailMessage.setSubject("蜗牛书店注册验证码");
        String code = producer.createText();
        mailMessage.setText("您的注册验证码,请尽快使用 "+code);

        javaMailSender.send(mailMessage);

        //redis中缓存验证码
        stringRedisTemplate.opsForValue().set(email,code,5,TimeUnit.MINUTES);

        //手动ack
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

}
