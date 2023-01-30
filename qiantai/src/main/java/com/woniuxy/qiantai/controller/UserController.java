package com.woniuxy.qiantai.controller;


import com.google.code.kaptcha.Producer;
import com.woniuxy.qiantai.entity.User;
import com.woniuxy.qiantai.service.UserService;
import com.woniuxy.qiantai.utils.CookieUtils;
import com.woniuxy.qiantai.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author woniumrwang
 * @since 2023-01-06 02:44:36
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    Producer producer;

    @Autowired
    UserService userService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    JavaMailSender javaMailSender;

    @RequestMapping("getKaptchaImage")
    public void getKaptchaImage(HttpServletResponse response,HttpSession httpSession) throws IOException {
        //生成验证码
        String codeText = producer.createText();
        codeText="0000"; //手动指定验证码,方便调试
        httpSession.setAttribute("kaptchaCode",codeText);

        BufferedImage codeImage = producer.createImage(codeText);

        response.setContentType("image/png");
        ImageIO.write(codeImage,"png",response.getOutputStream());

    }


    @PostMapping("login")
    public String login(String username, String password, String code,
                        HttpSession httpSession, Model model,HttpServletResponse response){

        //校验验证码
        String kaptchaCode = (String)httpSession.getAttribute("kaptchaCode");
        if (StringUtils.isEmpty(code) || !code.equals(kaptchaCode)){
            model.addAttribute("errorInfo","验证码错误");
            return "login";
        }

        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            model.addAttribute("errorInfo","用户名或密码错误");
            return "login";
        }

        User userByAccount = userService.getUserByAccount(username);
        if (null==userByAccount || !password.equals(userByAccount.getPassword())){
            model.addAttribute("errorInfo","用户名或密码错误");
            return "login";
        }

        //登录成功
        //httpSession.setAttribute("currentAccount",userByAccount.getAccount());
        String token = JwtUtils.createToken(userByAccount.getAccount(), 15);
        CookieUtils.setUserToken2Cookie(response,token);

        //在redis里也保存一份信息,有效期30分钟
        stringRedisTemplate.opsForValue().set(token,userByAccount.getAccount(),30, TimeUnit.MINUTES);

        return "redirect:/";
    }

    @RequestMapping("currentAccount")
    @ResponseBody
    public String currentAccount(HttpSession httpSession, HttpServletRequest request){
        String account="";

//        String currentAccount = (String)httpSession.getAttribute("currentAccount");
        String userTokenFromCookie = CookieUtils.getUserTokenFromCookie(request);
        String currentAccount = JwtUtils.getAccountWithoutException(userTokenFromCookie);
        account = StringUtils.isEmpty(currentAccount) ? account : currentAccount;

        return account;
    }


    @RequestMapping("logout")
    public String logout(HttpSession httpSession,HttpServletResponse response,HttpServletRequest request){
        //httpSession.removeAttribute("currentAccount");
        CookieUtils.deleteUserTokenFromCookie(response);

        //同时删除redis中的信息
        String userTokenFromCookie = CookieUtils.getUserTokenFromCookie(request);
        if (!StringUtils.isEmpty(userTokenFromCookie)){
            stringRedisTemplate.delete(userTokenFromCookie);
        }

        return "redirect:/";
    }

    @RequestMapping("redirectLoginHtml")
    public String redirectLoginHtml(){
        return "redirect:/login.html";
    }


    @RequestMapping("getEmailCode")
    @ResponseBody
    public String getEmailCode(String email){

        //正则表达式校验邮箱
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        boolean isOK = pattern.matcher(email).matches();
        if (!isOK){
            return "请输入正确邮箱";
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("woniumrwang@qq.com");
        message.setTo(email);  //用自己的邮箱做测试
        message.setSubject("蜗牛书店注册验证码");
        String code = producer.createText();
        message.setText("您的注册验证码,请尽快使用 "+code);

        javaMailSender.send(message);

        stringRedisTemplate.opsForValue().set(email,code,5,TimeUnit.MINUTES);

        return "ok";
    }

    @RequestMapping("reg")
    public String reg(String username,String password,String repass,String email,String emailCode,Model model){

        //校验邮箱验证码
        String emailCodeRedis = stringRedisTemplate.opsForValue().get(email);
        if (StringUtils.isEmpty(emailCodeRedis) || !emailCodeRedis.equals(emailCode)){
            model.addAttribute("errorInfo","邮箱验证码错误");
            return "register";
        }

        //校验用户名, 用户名要唯一
        if (StringUtils.isEmpty(username) || userService.getUserByAccount(username)!=null){
            model.addAttribute("errorInfo","用户名已被占用,请重试");
            return "register";
        }

        //校验邮箱,邮箱要唯一
        if(StringUtils.isEmpty(email) || userService.getUserByEmail(email)!=null){
            model.addAttribute("errorInfo","邮箱已被占用,请重试");
            return "register";
        }

        if(StringUtils.isEmpty(password) || StringUtils.isEmpty(repass) || !password.equals(repass)){
            model.addAttribute("errorInfo","请正确输入两次密码");
            return "register";
        }

        userService.reg(username,password,email);

        return "login";
    }


}

