package com.ch.booklib.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ch.booklib.entity.Booktype;
import com.ch.booklib.service.BooktypeService;
import com.ch.booklib.mapper.BooktypeMapper;
import org.springframework.stereotype.Service;

/**
* @author q2893
* @description 针对表【t_booktype】的数据库操作Service实现
* @createDate 2023-02-01 10:35:38
*/
@Service
public class BooktypeServiceImpl extends ServiceImpl<BooktypeMapper, Booktype>
    implements BooktypeService{

}




