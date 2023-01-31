package com.woniuxy.qiantai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniuxy.qiantai.entity.Address;
import com.woniuxy.qiantai.entity.Book;
import com.woniuxy.qiantai.mapper.AddressMapper;
import com.woniuxy.qiantai.mapper.BookMapper;
import com.woniuxy.qiantai.service.AddressService;
import com.woniuxy.qiantai.service.MyCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author woniumrwang
 * @since 2023-01-06 02:44:35
 */
@Service
public class MyCartServiceImpl implements MyCartService {

//    @Autowired
//    RedisTemplate;
//    StringRedisTemplate;
//    RedisTemplate<String,Object>

    @Autowired
    RedisTemplate<String,Object> stringObjectRedisTemplate;

    @Autowired
    BookMapper bookMapper;

    @Override
    public void addCart(Long userId, Long bookId) {

        Book book = bookMapper.selectById(bookId);

        HashOperations<String, Object, Object> hashOperations = stringObjectRedisTemplate.opsForHash();
        hashOperations.put(userId+"",bookId+"",book);

    }



}
