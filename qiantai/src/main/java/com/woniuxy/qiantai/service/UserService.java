package com.woniuxy.qiantai.service;

import com.woniuxy.qiantai.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author woniumrwang
 * @since 2023-01-06 02:44:36
 */
public interface UserService extends IService<User> {

    User getUserByAccount(String account);

    User getUserByEmail(String email);

    void reg(String username, String password, String email);
}
