package com.ch.booklib;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ch.booklib.entity.Book;
import com.ch.booklib.service.BookService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookLibApplicationTests {

    @Autowired
    BookService bookService;

    @Test
    void contextLoads() {
        UpdateWrapper<Book> updateWrapper = new
                UpdateWrapper<Book>().setSql("buycount=buycount+" + 2).eq("id", 1);
        boolean update = bookService.update(updateWrapper);
        System.out.println(update);
    }

}
