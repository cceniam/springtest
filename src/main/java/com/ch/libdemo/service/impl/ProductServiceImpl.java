package com.ch.libdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ch.libdemo.entity.Product;
import com.ch.libdemo.service.ProductService;
import com.ch.libdemo.mapper.ProductMapper;
import org.springframework.stereotype.Service;

/**
* @author q2893
* @description 针对表【t_product】的数据库操作Service实现
* @createDate 2023-02-04 16:22:43
*/
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
    implements ProductService{

}




