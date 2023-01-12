package com.woniuxy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class RedisDemoMainTest {

    @Autowired
    RedisTemplate<Object,Object> objectObjectRedisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @Test
    void testTemplate(){
        String key = "redisKey";
        String value = "redisValue";

        /**
         * redisTemplate.opsForValue() : 操作String数据类型  操作String的命令
         * redisTemplate.opsForList()  : 操作集合（List）数据类型  操作Lsit的命令
         * redisTemplate.opsForSet()   : 操作Set数据类型   操作Set中命令
         * redisTemplate.opsForHash()  : 操作hash数据类型   操作hash的命令
         * redisTemplate.opsForZSet()  : 操作Zset数据数据类型  操作Zset的命令
         *
         * stringRedisTemplate.opsForValue() : 操作String数据类型  操作String的命令
         * stringRedisTemplate.opsForList()  : 操作集合（List）数据类型  操作Lsit的命令
         * stringRedisTemplate.opsForSet()   : 操作Set数据类型   操作Set中命令
         * stringRedisTemplate.opsForHash()  : 操作hash数据类型   操作hash的命令
         * stringRedisTemplate.opsForZSet()  : 操作Zset数据数据类型  操作Zset的命令
         *
         * 上述两个对象的区别 ：
         * redisTemplate ：操作的是Java对象 ，redis不认识Java对象，向redis存储时 ，需要把Java对象进行序列化之后，才能存储 ，
         *                 SpringBoot内部提供了序列化机制，（我们可以自定义）
         * stringRedisTemplate ：他是可以操作Java中的字符串 ，只能操作字符串 ，redis是可以识别字符串，也是SpringBoot对于String
         *                 类型提供的序列化
         */

        //RedisTemplate<Object,Object>
        ValueOperations<Object, Object> objectObjectValueOperations = objectObjectRedisTemplate.opsForValue();
        objectObjectValueOperations.set(key,value);
        Object operationsValue = objectObjectValueOperations.get(key);
        System.out.println(operationsValue);

        //StringRedisTemplate
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        stringStringValueOperations.set(key,value);
        String operationsStringValue = stringStringValueOperations.get(key);
        System.out.println(operationsStringValue);


    }


}
