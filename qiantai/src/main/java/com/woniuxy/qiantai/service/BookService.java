package com.woniuxy.qiantai.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.qiantai.entity.Book;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author woniumrwang
 * @since 2023-01-06 02:44:35
 */
public interface BookService extends IService<Book> {

    Page<Book> getPageBook(Integer currentPage, Integer pageSize);


}
