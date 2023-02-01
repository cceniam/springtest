package com.ch.booklib.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ch.booklib.entity.Book;
import com.ch.booklib.service.BookService;
import com.ch.booklib.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author q2893
 * @description 针对表【t_book】的数据库操作Service实现
 * @createDate 2023-02-01 10:35:38
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {
    @Autowired
    BookMapper bookMapper;

    /**
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public Page<Book> getPageBook(Integer currentPage, Integer pageSize) {
        //分页
        Page<Book> bookPage = new Page<>(currentPage, pageSize);

        //查询条件
        QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<>();
        bookQueryWrapper.orderByDesc("buycount");

        Page<Book> bookPageResult = bookMapper.selectPage(bookPage, bookQueryWrapper);

        return bookPageResult;
    }

    /**
     * @param currentPage
     * @param pageSize
     * @param typeId
     * @return
     */
    @Override
    public Page<Book> getPageBooksByTypeId(Integer currentPage, Integer pageSize, Integer typeId) {
        //分页条件
        Page<Book> bookPage = new Page<>(currentPage, pageSize);

        //查询条件
        QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<>();
        bookQueryWrapper.eq("typeId", typeId);

        Page<Book> bookPageResult = bookMapper.selectPage(bookPage, bookQueryWrapper);

        return bookPageResult;
    }
}




