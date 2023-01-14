package com.woniuxy;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DelayConfig {

    //死信交换机
    @Bean
    public FanoutExchange deadLetterExchagne(){
        return new FanoutExchange("deadLetterExchange");
    }

    //死信队列
    @Bean
    public Queue deadLetterQueue(){
        return new Queue("deadLetterQueue");
    }

    //绑定死信交换机和死信队列
    @Bean
    public Binding deadLetterBinding(){
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchagne());
    }


    //正常交换机
    @Bean
    public FanoutExchange normalExchagne(){
        return new FanoutExchange("normalExchagne");
    }

    //正常队列, 需要绑定死信交换机
    //正常队列不能有消费者
    @Bean
    public Queue normalQueue(){
        return QueueBuilder.durable("normalQueue")
                .deadLetterExchange("deadLetterExchange")
                .deadLetterRoutingKey("")
                .ttl(20000)
                .build();
    }

    //绑定正常交换机和正常队列
    @Bean
    public Binding normalBinding(){
        return BindingBuilder.bind(normalQueue()).to(normalExchagne());
    }


}
