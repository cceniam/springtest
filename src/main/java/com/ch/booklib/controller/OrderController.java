package com.ch.booklib.controller;


import com.ch.booklib.entity.User;
import com.ch.booklib.service.OrderService;
import com.ch.booklib.service.UserService;
import com.ch.booklib.utils.CookieUtils;
import com.ch.booklib.utils.JwtUtils;
import com.ch.booklib.vo.CartOrder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author woniumrwang
 * @since 2023-01-06 02:44:36
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @RequestMapping("allItems")
    @ResponseBody
    public CartOrder allItems(@RequestParam("bookIds[]") Long[] bookIds, HttpServletRequest request){

        //获取用户信息
        User currentUser = getCurrentUser(request);

        //从购物车里获取详情
        CartOrder cartOrder = orderService.getCartOrder(currentUser.getId(), bookIds);

        return cartOrder;
    }


    private User getCurrentUser(HttpServletRequest request){
        String userTokenFromCookie = CookieUtils.getUserTokenFromCookie(request);
        String currentUserAccount = JwtUtils.getAccountWithoutException(userTokenFromCookie);
        User currentUser = userService.getUserByAccount(currentUserAccount);

        return currentUser;
    }


}

