package com.ch.booklib.service;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author woniumrwang
 * @since 2023-01-06 02:44:35
 */
public interface MyCartService {

    void addCart(Long userId, Long bookId);

    Collection<Object> getAllItem(Long userId);

    void updateItemNum(Long userId, Long bookId, Integer itemNum);

    BigDecimal calTotalPrice(Long userId, Long[] bookIds);
}
