package com.woniuxy;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

@Configuration
public class MyKeyGenerator {

    @Bean
    public KeyGenerator keyGenerator(){
        return new KeyGenerator(){

            @Override
            public Object generate(Object target, Method method, Object... params) {
                //这里可以通过 target method params 的唯一组合 对应的 最终生成的key也要唯一
                return target.toString()+"WONIU"+method.toString()+params.toString();
            }
        };
    }

}
