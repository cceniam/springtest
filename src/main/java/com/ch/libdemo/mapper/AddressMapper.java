package com.ch.libdemo.mapper;

import com.ch.libdemo.entity.Address;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Bean;
@Mapper

/**
* @author q2893
* @description 针对表【t_address】的数据库操作Mapper
* @createDate 2023-02-04 16:00:50
* @Entity com.ch.libdemo.entity.Address
*/
public interface AddressMapper extends BaseMapper<Address> {

}




