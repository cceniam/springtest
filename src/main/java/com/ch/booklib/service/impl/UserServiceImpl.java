package com.ch.booklib.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ch.booklib.entity.User;
import com.ch.booklib.service.UserService;
import com.ch.booklib.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
* @author q2893
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2023-02-01 10:35:38
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserByAccount(String account){

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",account);

        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
    public User getUserByEmail(String email) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email",email);

        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
    public void reg(String username, String password, String email) {
        User user = new User();
        user.setAccount(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setCreatetime(new Date());
        user.setUpdatetime(new Date());
        user.setState(1);

        userMapper.insert(user);
    }
}




