package com.woniuxy.qiantai.service.impl;

import com.woniuxy.qiantai.entity.User;
import com.woniuxy.qiantai.mapper.UserMapper;
import com.woniuxy.qiantai.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
