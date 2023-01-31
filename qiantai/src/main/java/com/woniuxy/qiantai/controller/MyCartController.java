package com.woniuxy.qiantai.controller;

import com.woniuxy.qiantai.entity.User;
import com.woniuxy.qiantai.service.MyCartService;
import com.woniuxy.qiantai.service.UserService;
import com.woniuxy.qiantai.utils.CookieUtils;
import com.woniuxy.qiantai.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("mycart")
public class MyCartController {

    /*
    1.用户登录
    2.选择书籍加入购物车
    3.我的购物车页面显示已加购的图书
    4.选择购物车中的图书进行结算(订单预览)
    5.订单结算.....
     */

    @Autowired
    UserService userService;

    @Autowired
    MyCartService myCartService;

    //图书加入购物车
    @RequestMapping("add")
    @ResponseBody
    public String add(Long bookId, HttpServletRequest request){

        //获取当前用户信息
        String userTokenFromCookie = CookieUtils.getUserTokenFromCookie(request);
        String currentUserAccount = JwtUtils.getAccountWithoutException(userTokenFromCookie);
        User currentUser = userService.getUserByAccount(currentUserAccount);

        //将书籍加入到该用户的购物车
        myCartService.addCart(currentUser.getId(),bookId);

        return "ok";
    }




    //获取购物车信息


}
