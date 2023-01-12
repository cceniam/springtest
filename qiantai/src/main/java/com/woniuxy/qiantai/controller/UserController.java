package com.woniuxy.qiantai.controller;


import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author woniumrwang
 * @since 2023-01-06 02:44:36
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    Producer producer;

    @RequestMapping("getKaptchaImage")
    public void getKaptchaImage(HttpServletResponse response) throws IOException {
        //生成验证码
        String codeText = producer.createText();
        BufferedImage codeImage = producer.createImage(codeText);

        response.setContentType("image/png");
        ImageIO.write(codeImage,"png",response.getOutputStream());

    }



}

