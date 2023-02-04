package com.ch.libdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ch.libdemo.entity.Address;
import com.ch.libdemo.service.AddressService;
import com.ch.libdemo.mapper.AddressMapper;
import org.springframework.stereotype.Service;

/**
* @author q2893
* @description 针对表【t_address】的数据库操作Service实现
* @createDate 2023-02-04 16:00:50
*/
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address>
    implements AddressService{

}




