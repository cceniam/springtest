package com.ch.libdemo;

import com.ch.libdemo.service.OrderService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LibdemoApplicationTests {

    @Resource
    OrderService orderService;

    @Test
    void contextLoads() {
        System.out.println(orderService.list());


    }

}
