package com.woniuxy.qiantai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.woniuxy.qiantai.entity.Address;

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

}
