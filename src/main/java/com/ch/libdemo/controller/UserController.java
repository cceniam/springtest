package com.ch.libdemo.controller;

import com.ch.libdemo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.Banner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")

public class UserController {
    @Resource
    UserService userService;
    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Resource
    JavaMailSender javaMailSender;
    @Resource
    RabbitTemplate rabbitTemplate;

    @RequestMapping("reg")
    public String reg(String username, String password, String emailCode, Model model){

    }

}
