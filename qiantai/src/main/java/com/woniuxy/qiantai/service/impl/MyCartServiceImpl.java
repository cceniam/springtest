package com.woniuxy.qiantai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniuxy.qiantai.entity.Address;
import com.woniuxy.qiantai.entity.Book;
import com.woniuxy.qiantai.mapper.AddressMapper;
import com.woniuxy.qiantai.mapper.BookMapper;
import com.woniuxy.qiantai.service.AddressService;
import com.woniuxy.qiantai.service.MyCartService;
import com.woniuxy.qiantai.vo.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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

        //获取图书信息
        Book book = bookMapper.selectById(bookId);

        //加入购物车,数据存在redis中
        HashOperations<String, Object, Object> hashOperations = stringObjectRedisTemplate.opsForHash();
//        hashOperations.put(userId+"",bookId+"",book);
        Object item = hashOperations.get(userId + "", bookId + "");
        System.out.println(item);
        if (item==null){
            //该图书还没有加入过购物车   直接组装CartItem写入redis
            CartItem cartItem = new CartItem();
            cartItem.setBookId(book.getId());
            cartItem.setBookName(book.getName());
            cartItem.setImgSrc(book.getImgsrc());
            cartItem.setBookPrice(book.getPrice());
            cartItem.setItemNum(1);
            cartItem.setSumPrice(book.getPrice().multiply(new BigDecimal("1")));

            hashOperations.put(userId+"",bookId+"",cartItem);
        }else {
            //该图书已经加入过购物车  修改购买数量和小计即可.
            CartItem cartItem = (CartItem) item;
            cartItem.setItemNum(cartItem.getItemNum()+1);  //购买数量+1
            //计算方法
            //方式一: price x itemNum
            //方式二: 原有sumPrice + price
            cartItem.setSumPrice(cartItem.getSumPrice().add(cartItem.getBookPrice()));

            hashOperations.put(userId+"",bookId+"",cartItem);
        }

        //



    }



}
