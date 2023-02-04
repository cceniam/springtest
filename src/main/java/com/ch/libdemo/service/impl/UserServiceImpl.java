package com.ch.libdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ch.libdemo.entity.User;
import com.ch.libdemo.service.UserService;
import com.ch.libdemo.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author q2893
 * @description 针对表【t_user】的数据库操作Service实现
 * @createDate 2023-02-04 17:42:37
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Override
    public boolean isEmailExist(String email) {
        List<User> email1 = this.list(new QueryWrapper<User>().eq("email", email));
        if (email1.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isUserExist(String username) {
        List<User> users = this.list(new QueryWrapper<User>().eq("username", username));
        if (users.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String reg(String username, String password, String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        this.save(user);
        return "注册成功";
    }
}




