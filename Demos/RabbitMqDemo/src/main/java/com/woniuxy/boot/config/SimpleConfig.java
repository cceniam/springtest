package com.woniuxy.boot.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleConfig {

    //创建队列
    @Bean
    public Queue simpleQueue(){
        return new Queue("simpleQueue");
    }

}
