package com.woniuxy.qiantai.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.qiantai.entity.Book;
import com.woniuxy.qiantai.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author woniumrwang
 * @since 2023-01-06 02:44:35
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;


    @RequestMapping("topN")
    public List<Book> topN(Integer n){

        Page<Book> pageBook = bookService.getPageBook(1, n);
        List<Book> records = pageBook.getRecords();

        return records;
    }



}

