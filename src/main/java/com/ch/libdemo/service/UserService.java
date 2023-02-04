package com.ch.libdemo.service;

import com.ch.libdemo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author q2893
* @description 针对表【t_user】的数据库操作Service
* @createDate 2023-02-04 17:42:37
*/
public interface UserService extends IService<User> {

    boolean isEmailExist(String email);

    boolean isUserExist(String username);

    String reg(String username, String password, String email);
}
