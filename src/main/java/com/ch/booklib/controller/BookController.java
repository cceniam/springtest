package com.ch.booklib.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ch.booklib.entity.Book;
import com.ch.booklib.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author woniumrwang
 * @since 2023-01-06 02:44:35
 */
@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;


    @RequestMapping("topN")
    @ResponseBody
    public List<Book> topN(Integer n){

        Page<Book> pageBook = bookService.getPageBook(1, n);
        List<Book> records = pageBook.getRecords();

        return records;
    }

    @RequestMapping("singleBook")
    public String singleBook(Long bookId, Model model){
        Book book = bookService.getById(bookId);
        model.addAttribute("book",book);

        return "singleBook";
    }


    @RequestMapping("booksOfType")
    public String booksOfType(Long typeId, Model model){
        model.addAttribute("typeId",typeId);
        return "booksOfType";
    }

    @RequestMapping("pageBooksOfType")
    @ResponseBody
    public Page<Book> pageBooksOfType(
//            @RequestParam(value = "page",defaultValue = "1") Integer currentPage,
            //defaultValue指定默认值, 是否指定默认值根据需求来
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "9") Integer pageSize,
            Integer typeId
    ){
        Page<Book> pageBooksByTypeId = bookService.getPageBooksByTypeId(currentPage, pageSize, typeId);

        return pageBooksByTypeId;
    }
@RequestMapping("tocrud")
public String toCRUD(){
        return "redirect:carcrud";
}
    @RequestMapping("crud")
    @ResponseBody
    public List<Book> crud(){
        return bookService.list();
    }


}

