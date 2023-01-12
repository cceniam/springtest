package com.woniuxy;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Queryer {

    //Cacheable 指定缓存的key
    //value别名cacheNames  就是分组的组名
    @Cacheable(value = "queryer",key = "#root.targetClass+#root.methodName")
    //queryer ::  class com.woniuxy.Queryer  queryAll
    public List queryAll(){

        System.out.println("执行查询所有数据");
        ArrayList<Object> objects = new ArrayList<>(2);
        objects.add("name");
        objects.add(new Date());
        return objects;
    }

}
