package com.woniuxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookTypeService {

    @Autowired
    BooktypeMapper booktypeMapper;

    @Cacheable(value = "bookType",key = "#root.targetClass+#root.methodName")
    public List<Booktype> getAll(){
        List<Booktype> booktypes = booktypeMapper.selectList(null);

        return booktypes;
    }

    public Booktype getById(Integer typeId){
        Booktype booktype = booktypeMapper.selectById(typeId);
        return booktype;
    }

    @CacheEvict(value = "bookType",allEntries = true)
    public void add(Booktype booktype){
        int insert = booktypeMapper.insert(booktype);
        //增删改可能造成 数据库和缓存 数据不一致的问题

        //方案一: 同步修改缓存中的数据

        //方案二: 干掉缓存  采用
//        booktypes = null;
    }

    @CacheEvict(value = "bookType",allEntries = true)
    public void deleteById(Integer typeId){
        int i = booktypeMapper.deleteById(typeId);
//        booktypes = null;
    }

    @CacheEvict(value = "bookType",allEntries = true)
    public void updateById(Booktype booktype){
        int i = booktypeMapper.updateById(booktype);
//        booktypes = null;
    }


}
