package com.ch.libdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ch.libdemo.entity.Cart;
import com.ch.libdemo.service.CartService;
import com.ch.libdemo.mapper.CartMapper;
import org.springframework.stereotype.Service;

/**
* @author q2893
* @description 针对表【t_cart】的数据库操作Service实现
* @createDate 2023-02-04 16:22:43
*/
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart>
    implements CartService{

}




