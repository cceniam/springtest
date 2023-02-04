package com.ch.libdemo.controller;

import com.ch.libdemo.service.UserService;
import com.google.code.kaptcha.Producer;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/user")

public class UserController {
    @Resource
    Producer producer;
    @Resource
    UserService userService;
    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Resource
    JavaMailSender javaMailSender;
    @Resource
    RabbitTemplate rabbitTemplate;
//    注册
    @RequestMapping("reg")
    public String reg(String username, String password, String repass, String email, String emailCode, Model model) {
//        获取redis中的验证码
        String redisCode = stringRedisTemplate.opsForValue().get(username);
//        判断验证码是否正确
        if(!emailCode.equals(redisCode)){
            model.addAttribute("msg","验证码错误");
            return "register";
        }
//        判断用户名是否存在
        if(userService.isUserExist(username)){
            model.addAttribute("msg","用户名已存在");
            return "register";
        }
//        判断密码是否一致
        if(!password.equals(repass)){
            model.addAttribute("msg","两次密码不一致");
            return "register";
        }
//        调用service层的注册方法
        String msg = userService.reg(username, password, email);
        return "login";


    }
//    获取验证码
    @RequestMapping("getEmailCode")
    @ResponseBody
    public String getEmailCode(String email){
//        邮箱正则
        String emailRegex = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        if(!Pattern.matches(emailRegex,email)){
            return "邮箱格式不正确";
        }
//        判断邮箱是否已经注册
        if(userService.isEmailExist(email)){
            return "邮箱已经注册";
        }
//创建简单邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("289332602@qq.com");
        message.setTo(email);  //用自己的邮箱做测试
        message.setSubject("蜗牛书店注册验证码");
        String code = producer.createText();
        message.setText("您的注册验证码,请尽快使用 "+code);

        javaMailSender.send(message);
//        设置验证码有效期,5分钟,并存入redis
        stringRedisTemplate.opsForValue().set(email,code,5, TimeUnit.MINUTES);
//        将验证码发送到mq队列
        rabbitTemplate.convertAndSend("sendEmailExchange","sendEmail",email);

        return "ok";

    }

//    登陆
    @RequestMapping("login")
    public String login(String username, String password, Model model){

        return "";
    }
}
