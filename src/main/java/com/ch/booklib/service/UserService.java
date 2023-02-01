package com.ch.booklib.service;

import com.ch.booklib.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author q2893
* @description 针对表【t_user】的数据库操作Service
* @createDate 2023-02-01 10:35:38
*/
public interface UserService extends IService<User> {
    User getUserByAccount(String account);

    User getUserByEmail(String email);

    void reg(String username, String password, String email);

}
