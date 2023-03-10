package com.ch.booklib.service.impl;

import com.ch.booklib.entity.Book;
import com.ch.booklib.mapper.BookMapper;
import com.ch.booklib.service.MyCartService;
import com.ch.booklib.vo.CartItem;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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

    @Resource
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

    @Override
    public Collection<Object> getAllItem(Long userId) {

        HashOperations<String, Object, Object> hashOperations = stringObjectRedisTemplate.opsForHash();
        Map<Object, Object> entries = hashOperations.entries(userId + "");
        Collection<Object> allItems = entries.values();

        return allItems;
    }

    @Override
    public void updateItemNum(Long userId, Long bookId, Integer itemNum) {
        HashOperations<String, Object, Object> hashOperations = stringObjectRedisTemplate.opsForHash();
        CartItem cartItem = (CartItem)hashOperations.get(userId + "", bookId + "");

        Integer currentItemNum = cartItem.getItemNum();

        if (itemNum == 1){
            //购买数量+1

            cartItem.setItemNum(cartItem.getItemNum()+1);  //购买数量+1
            cartItem.setSumPrice(cartItem.getSumPrice().add(cartItem.getBookPrice()));

            hashOperations.put(userId+"",bookId+"",cartItem);

        } else if (itemNum == -1) {
            //购买数量减一
            Integer newItemNum = cartItem.getItemNum() - 1;
            if(newItemNum<1){
                //做删除操作
                hashOperations.delete(userId+"",bookId+"");
            }else {
                //更新操作
                cartItem.setItemNum(cartItem.getItemNum()-1);  //购买数量+1
                cartItem.setSumPrice(cartItem.getSumPrice().subtract(cartItem.getBookPrice()));

                hashOperations.put(userId+"",bookId+"",cartItem);
            }

        }else {
            //认为是0,删除该项
            hashOperations.delete(userId+"",bookId+"");
        }


    }

    @Override
    public BigDecimal calTotalPrice(Long userId, Long[] bookIds) {

        HashOperations<String, Object, Object> hashOperations = stringObjectRedisTemplate.opsForHash();
        List<Object> allCartItems = hashOperations.values(userId + "");

        //计算总价
        BigDecimal totalPrice = new BigDecimal("0.00");
        for(Object item : allCartItems){
            CartItem cartItem = (CartItem)item;
            for(Long bookId : bookIds){
                //只有当前项的id在bookIds中时才作为计算总价的一部分
                if (cartItem.getBookId().equals(bookId)){
                    totalPrice = totalPrice.add(cartItem.getSumPrice());
                }
            }
        }

        return totalPrice;
    }


}
