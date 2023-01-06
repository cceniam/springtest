package com.woniuxy.qiantai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.qiantai.entity.Book;
import com.woniuxy.qiantai.mapper.BookMapper;
import com.woniuxy.qiantai.service.BookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author woniumrwang
 * @since 2023-01-06 02:44:35
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Autowired
    BookMapper bookMapper;

    @Override
    public Page<Book> getPageBook(Integer currentPage, Integer pageSize){
        //分页
        Page<Book> bookPage = new Page<>(currentPage,pageSize);

        //查询条件
        QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<>();
        bookQueryWrapper.orderByDesc("buycount");

        Page<Book> bookPageResult = bookMapper.selectPage(bookPage, bookQueryWrapper);

        return bookPageResult;
    }



}
