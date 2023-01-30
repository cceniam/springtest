package com.woniuxy.qiantai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniuxy.qiantai.entity.User;
import com.woniuxy.qiantai.mapper.UserMapper;
import com.woniuxy.qiantai.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author woniumrwang
 * @since 2023-01-06 02:44:36
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

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
