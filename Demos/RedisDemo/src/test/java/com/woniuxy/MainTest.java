package com.woniuxy;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

public class MainTest {

    @Test
    public void testJedis(){
        //建立连接
        Jedis jedis = new Jedis("192.168.217.101",6379);

        //测试连接是否ok
        String pingResult = jedis.ping();
        System.out.println(pingResult);

        String setResult = jedis.set("username", "jack");
        System.out.println(setResult);

        //测试获取
        String usernameValue = jedis.get("username");
        System.out.println(usernameValue);

        //测试修改
        String setResult2 = jedis.set("username", "jack222");
        System.out.println(setResult2);
        String usernameValue2 = jedis.get("username");
        System.out.println(usernameValue2);

        //测试删除
        Long delResult = jedis.del("username");
        System.out.println(delResult);

        //测试list
        String[] names = {"jim","tom","lucy","lily"};
        jedis.lpush("names",names);

        List<String> namesList = jedis.lrange("names", 0, -1);
//        namesList.forEach(item-> System.out.println(item));
        namesList.forEach(System.out::println);



    }


}
