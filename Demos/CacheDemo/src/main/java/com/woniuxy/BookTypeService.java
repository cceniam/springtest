package com.woniuxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookTypeService {

    @Autowired
    BooktypeMapper booktypeMapper;

    List<Booktype> booktypes;

    public List<Booktype> getAll(){
        if (null==booktypes){
            booktypes = booktypeMapper.selectList(null);
            System.out.println("从数据库查询数据");
        }else {
            System.out.println("直接从缓存(属性实现)中拿数据");
        }

        return booktypes;
    }

    public Booktype getById(Integer typeId){
        Booktype booktype = booktypeMapper.selectById(typeId);
        return booktype;
    }

    public void add(Booktype booktype){
        int insert = booktypeMapper.insert(booktype);
        //增删改可能造成 数据库和缓存 数据不一致的问题

        //方案一: 同步修改缓存中的数据

        //方案二: 干掉缓存  采用
        booktypes = null;
    }

    public void deleteById(Integer typeId){
        int i = booktypeMapper.deleteById(typeId);
        booktypes = null;
    }

    public void updateById(Booktype booktype){
        int i = booktypeMapper.updateById(booktype);
        booktypes = null;
    }


}
