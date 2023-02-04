package com.ch.libdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ch.libdemo.entity.User;
import com.ch.libdemo.service.UserService;
import com.ch.libdemo.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author q2893
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2023-02-04 16:00:51
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




