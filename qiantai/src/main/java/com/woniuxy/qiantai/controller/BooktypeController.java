package com.woniuxy.qiantai.controller;


import com.woniuxy.qiantai.entity.Booktype;
import com.woniuxy.qiantai.service.BooktypeService;
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
 * @since 2023-01-06 02:44:36
 */
@RestController
@RequestMapping("/booktype")
public class BooktypeController {

    @Autowired
    BooktypeService booktypeService;

    @RequestMapping("all")
    public List<Booktype> all(){
        List<Booktype> allBookType = booktypeService.list();

        return allBookType;
    }



}

