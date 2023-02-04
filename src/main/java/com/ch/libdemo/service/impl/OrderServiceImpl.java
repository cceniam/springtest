package com.ch.libdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ch.libdemo.entity.Order;
import com.ch.libdemo.service.OrderService;
import com.ch.libdemo.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
* @author q2893
* @description 针对表【t_order】的数据库操作Service实现
* @createDate 2023-02-04 16:00:51
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

}




