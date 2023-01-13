package com.woniuxy.boot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectConfig {

    //队列 起名：TestDirectQueue
    @Bean
    public Queue TestDirectQueue1() {
        return new Queue("TestDirectQueue1",true);
    }

    @Bean
    public Queue TestDirectQueue2() {
        return new Queue("TestDirectQueue2",true);
    }

    //Direct交换机 起名：TestDirectExchange
    @Bean
    DirectExchange TestDirectExchange() {
        return new DirectExchange("TestDirectExchange");
    }

    //绑定  将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
    @Bean
    Binding bindingDirect1() {
        //因为Spring的处理,直接调用 TestDirectQueue1() 拿到的仍然是一个单例对象
        return BindingBuilder.bind(TestDirectQueue1()).to(TestDirectExchange()).with("TestDirectRouting1");
    }
    @Bean
    Binding bindingDirect2() {
        return BindingBuilder.bind(TestDirectQueue2()).to(TestDirectExchange()).with("TestDirectRouting2");
    }


}
