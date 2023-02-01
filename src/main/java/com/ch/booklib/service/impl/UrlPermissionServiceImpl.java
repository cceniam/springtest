package com.ch.booklib.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ch.booklib.entity.UrlPermission;
import com.ch.booklib.service.UrlPermissionService;
import com.ch.booklib.mapper.UrlPermissionMapper;
import org.springframework.stereotype.Service;

/**
* @author q2893
* @description 针对表【t_url_permission(菜单和接口权限定义)】的数据库操作Service实现
* @createDate 2023-02-01 10:35:38
*/
@Service
public class UrlPermissionServiceImpl extends ServiceImpl<UrlPermissionMapper, UrlPermission>
    implements UrlPermissionService{

}




