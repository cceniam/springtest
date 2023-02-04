package com.ch.libdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ch.libdemo.entity.Orderitem;
import com.ch.libdemo.service.OrderitemService;
import com.ch.libdemo.mapper.OrderitemMapper;
import org.springframework.stereotype.Service;

/**
* @author q2893
* @description 针对表【t_orderitem】的数据库操作Service实现
* @createDate 2023-02-04 17:42:37
*/
@Service
public class OrderitemServiceImpl extends ServiceImpl<OrderitemMapper, Orderitem>
    implements OrderitemService{

}




