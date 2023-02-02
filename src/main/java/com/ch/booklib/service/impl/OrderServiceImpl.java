package com.ch.booklib.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ch.booklib.entity.Book;
import com.ch.booklib.entity.Item;
import com.ch.booklib.entity.Order;
import com.ch.booklib.entity.User;
import com.ch.booklib.service.BookService;
import com.ch.booklib.service.ItemService;
import com.ch.booklib.service.OrderService;
import com.ch.booklib.mapper.OrderMapper;
import com.ch.booklib.vo.CartItem;
import com.ch.booklib.vo.CartOrder;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author q2893
 * @description 针对表【t_order】的数据库操作Service实现
 * @createDate 2023-02-01 10:35:38
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    RedisTemplate<String, Object> stringObjectRedisTemplate;
    @Autowired
    ItemService itemService;
    @Resource
    BookService bookService;


    @Override
    public CartOrder getCartOrder(Long userId, Long[] bookIds) {

        HashOperations<String, Object, Object> hashOperations = stringObjectRedisTemplate.opsForHash();
        List<Object> allCartItems = hashOperations.values(userId + "");

        CartOrder cartOrder = new CartOrder();
        cartOrder.setCartItems(new ArrayList<>());

        //计算总价
        BigDecimal totalPrice = new BigDecimal("0.00");
        for (Object item : allCartItems) {
            CartItem cartItem = (CartItem) item;
            for (Long bookId : bookIds) {
                //只有当前项的id在bookIds中时才作为计算总价的一部分
                //同时拿到要结算的cartItem
                if (cartItem.getBookId().equals(bookId)) {
                    cartOrder.getCartItems().add(cartItem);
                    totalPrice = totalPrice.add(cartItem.getSumPrice());
                }
            }
        }

        cartOrder.setTotalPrice(totalPrice);

        return cartOrder;
    }

    /**
     * @param currentUser
     * @param bookIds
     * @param addressId
     */
    @Override
    @Transactional
    public void createOrder(User currentUser, Long[] bookIds, Integer addressId) {
//        获取要结算项目的详情
        CartOrder cartOrder = getCartOrder(currentUser.getId(), bookIds);
//        创建订单
        Order order = new Order();
//        将订单写入订单表
        String orderNum = "WONIU" + new Date().getTime();
        order.setOrdernum(orderNum);
//        order.setTotalprice(myCartService.calTotalPrice(currentUser.getId(),bookIds));
        order.setTotalprice(cartOrder.getTotalPrice());
        order.setUserid(currentUser.getId());
        order.setAddressid(addressId.longValue());  //addressId需要转换为logn值
        order.setCreatetime(new Date());
        order.setState(1);  //订单状态 1.未支付  2 . 已支付  3.退款中  4. 已退款  5.已取消
        boolean save = this.save(order);
//        储存订单详情
        List<CartItem> cartItems = cartOrder.getCartItems();
        for (CartItem cartItem : cartItems) {
            Item item = new Item();
            item.setBookid(cartItem.getBookId());
            item.setBookname(cartItem.getBookName());
            item.setPrice(cartItem.getBookPrice());
            item.setBcount(cartItem.getItemNum());
            item.setSumprice(cartItem.getSumPrice());
            item.setOrderid(order.getId());
            item.setCreatetime(new Date());
            item.setState(1);  //订单项状态,我们这里取默认值1
            itemService.save(item);
//        更新库存和购买量
            QueryWrapper<Book> wrapper = new QueryWrapper<>();
            wrapper.eq("id", item.getBookid()).ge("storecount", cartItem.getItemNum());
            boolean update = bookService.update(wrapper);
            if (!update) {
                System.out.println("数量不足");
            }
            UpdateWrapper<Book> updateWrapper = new
                    UpdateWrapper<Book>().setSql("buycount=buycount+" + cartItem.getItemNum()).eq("id", cartItem.getBookId());
            boolean update1 = bookService.update(updateWrapper);


//        从购物车中删除已经结算的书籍;
        }


    }
}




