package com.ch.libdemo.mapper;

import com.ch.libdemo.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author q2893
* @description 针对表【t_product】的数据库操作Mapper
* @createDate 2023-02-04 17:42:37
* @Entity com.ch.libdemo.entity.Product
*/
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

}




