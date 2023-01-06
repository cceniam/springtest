package com.woniuxy.qiantai.service.impl;

import com.woniuxy.qiantai.entity.Book;
import com.woniuxy.qiantai.mapper.BookMapper;
import com.woniuxy.qiantai.service.BookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
