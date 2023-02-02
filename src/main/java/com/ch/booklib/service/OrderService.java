package com.ch.booklib.service;

import com.ch.booklib.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ch.booklib.entity.User;
import com.ch.booklib.vo.CartOrder;

/**
* @author q2893
* @description 针对表【t_order】的数据库操作Service
* @createDate 2023-02-01 10:35:38
*/
public interface OrderService extends IService<Order> {
    CartOrder getCartOrder(Long userId, Long[] bookIds);

    void createOrder(User currentUser, Long[] bookIds, Integer addressId);
}
