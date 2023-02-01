package com.ch.booklib.controller;

import com.ch.booklib.entity.User;
import com.ch.booklib.service.MyCartService;
import com.ch.booklib.service.UserService;
import com.ch.booklib.utils.CookieUtils;
import com.ch.booklib.utils.JSONUtils;
import com.ch.booklib.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Collection;

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
        User currentUser = getCurrentUser(request);

        //将书籍加入到该用户的购物车
        myCartService.addCart(currentUser.getId(),bookId);

        return "ok";
    }




    //获取购物车信息
    @RequestMapping("allItem")
    @ResponseBody
    public Collection<Object> allItem(HttpServletRequest request){

        User currentUser = getCurrentUser(request);

        Collection<Object> allItem = myCartService.getAllItem(currentUser.getId());

        return allItem;
    }


    private User getCurrentUser(HttpServletRequest request){
        String userTokenFromCookie = CookieUtils.getUserTokenFromCookie(request);
        String currentUserAccount = JwtUtils.getAccountWithoutException(userTokenFromCookie);
        User currentUser = userService.getUserByAccount(currentUserAccount);

        return currentUser;
    }

    @RequestMapping("updateItemNum")
    @ResponseBody
    public void updateItemNum(Long bookId, HttpServletRequest request,Integer itemNum){
        //itemNum 取值  -1 ,1 ,0 对应操作为  购买数量 -1,+1,删除

        User currentUser = getCurrentUser(request);

        myCartService.updateItemNum(currentUser.getId(),bookId,itemNum);

    }

    @RequestMapping("calTotalPrice")
    @ResponseBody
    public BigDecimal calTotalPrice(@RequestParam("bookIds[]") Long[] bookIds, HttpServletRequest request){

        User currentUser = getCurrentUser(request);
        BigDecimal totalPrice = myCartService.calTotalPrice(currentUser.getId(), bookIds);

        return totalPrice;
    }

    @RequestMapping("toOrderPreview")
    public String toOrderPreview(Long[] bookIds, Model model){
        System.out.println(bookIds);
        String bookIdsJson = JSONUtils.createJson(bookIds);
        System.out.println(bookIdsJson);

//        model.addAttribute("bookIds",bookIds);
        //给前端传数据的时候,传json数据
        model.addAttribute("bookIds",bookIdsJson);

        return "orderPreview";
    }

}
