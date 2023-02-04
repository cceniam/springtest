package com.ch.libdemo.mapper;

import com.ch.libdemo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author q2893
* @description 针对表【t_user】的数据库操作Mapper
* @createDate 2023-02-04 16:00:51
* @Entity com.ch.libdemo.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




