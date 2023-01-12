package com.woniuxy;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.woniuxy.utils.JedisPoolUtil;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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


    @Test
    public void testJedisPool(){
        //Properties
        Jedis jedis = JedisPoolUtil.getJedis();

        System.out.println(jedis.ping("nihaoya"));
        //.....

        JedisPoolUtil.close(jedis);

    }

    @Test
    public void testJson(){

        User user = new User();
        user.setName("woniu");
        user.setAge(18);

        //GSON
        Gson gson = new Gson();
        String gson_json = gson.toJson(user);
        System.out.println(gson_json);

        User user_gson = gson.fromJson(gson_json, User.class);
        System.out.println(user_gson);

        //Fastjson
        String fastjson_json = JSON.toJSONString(user);
        System.out.println(fastjson_json);

        Object parse = JSON.parse(fastjson_json);
        System.out.println(parse);

        User user_fastjson = JSON.parseObject(fastjson_json, User.class);
        System.out.println(user_fastjson);


        //测试list
        User user2 = new User();
        user2.setName("woniu2");
        user2.setAge(19);

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user2);

        //注意这里gson反序列化之后类型变掉了
        String userList_json_gson = gson.toJson(userList);
        System.out.println(userList_json_gson);
        ArrayList userList_gson = gson.fromJson(userList_json_gson, new ArrayList<User>().getClass());

        //fastjson反序列化符合预期
        String userList_json_fast = JSON.toJSONString(userList);
        System.out.println(userList_json_fast);
        List<User> userList_fast = JSON.parseArray(userList_json_fast, User.class);

        //还有一个工具叫jackson
    }




}
