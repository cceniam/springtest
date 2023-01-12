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

    @Cacheable(value = "queryer",key = "#root.methodName+#root.args[0]+#root.args[1]+#root.args[2]")
    public List queryAllPage(int now, int size , String name){

        System.out.println("执行查询所有数据 queryAllPage");
        ArrayList<Object> objects = new ArrayList<>(2);
        objects.add("name");
        objects.add(new Date());
        return objects;
    }


    //p 就是 params
    @Cacheable(value = "queryer",key = "#root.methodName+#p0+#p1+#p2")
    public List queryAllPage2(int now, int size , String name){

        System.out.println("执行查询所有数据 queryAllPage2");
        ArrayList<Object> objects = new ArrayList<>(2);
        objects.add("name");
        objects.add(new Date());
        return objects;
    }



}
