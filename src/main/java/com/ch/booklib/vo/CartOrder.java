package com.ch.booklib.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CartOrder {

    /**
     * 要结算的所有购物项
     */
    List<CartItem>  CartItems;

    /**
     * 结算总价
     */
    BigDecimal totalPrice;

}
