package com.woniuxy.qiantai;

import com.woniuxy.qiantai.entity.User;
import com.woniuxy.qiantai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@SpringBootApplication
@Controller
public class QianTaiMain {
    public static void main(String[] args) {
        SpringApplication.run(QianTaiMain.class);
    }

    @Autowired
    UserService userService;

    @RequestMapping("/")
//    @ResponseBody
    public String testCode(){
//        List<User> userList = userService.list();

        return "index";
    }

}