package com.woniuxy.qiantai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniuxy.qiantai.entity.User;
import com.woniuxy.qiantai.mapper.UserMapper;
import com.woniuxy.qiantai.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
