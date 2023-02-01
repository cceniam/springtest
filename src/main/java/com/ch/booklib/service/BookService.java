package com.ch.booklib.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ch.booklib.entity.Book;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author q2893
* @description 针对表【t_book】的数据库操作Service
* @createDate 2023-02-01 10:35:38
*/
public interface BookService extends IService<Book> {

    Page<Book> getPageBook(Integer currentPage, Integer pageSize);


    Page<Book> getPageBooksByTypeId(Integer currentPage, Integer pageSize, Integer typeId);

}


