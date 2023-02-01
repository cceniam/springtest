package com.ch.booklib.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ch.booklib.entity.Order;
import com.ch.booklib.service.OrderService;
import com.ch.booklib.mapper.OrderMapper;
import com.ch.booklib.vo.CartItem;
import com.ch.booklib.vo.CartOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
* @author q2893
* @description 针对表【t_order】的数据库操作Service实现
* @createDate 2023-02-01 10:35:38
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{
    @Autowired
    RedisTemplate<String,Object> stringObjectRedisTemplate;


    @Override
    public CartOrder getCartOrder(Long userId, Long[] bookIds) {

        HashOperations<String, Object, Object> hashOperations = stringObjectRedisTemplate.opsForHash();
        List<Object> allCartItems = hashOperations.values(userId + "");

        CartOrder cartOrder = new CartOrder();
        cartOrder.setCartItems(new ArrayList<>());

        //计算总价
        BigDecimal totalPrice = new BigDecimal("0.00");
        for(Object item : allCartItems){
            CartItem cartItem = (CartItem)item;
            for(Long bookId : bookIds){
                //只有当前项的id在bookIds中时才作为计算总价的一部分
                //同时拿到要结算的cartItem
                if (cartItem.getBookId().equals(bookId)){
                    cartOrder.getCartItems().add(cartItem);
                    totalPrice = totalPrice.add(cartItem.getSumPrice());
                }
            }
        }

        cartOrder.setTotalPrice(totalPrice);

        return cartOrder;
    }
}




