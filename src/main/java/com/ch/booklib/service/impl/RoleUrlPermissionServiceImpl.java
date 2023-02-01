package com.ch.booklib.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ch.booklib.entity.RoleUrlPermission;
import com.ch.booklib.service.RoleUrlPermissionService;
import com.ch.booklib.mapper.RoleUrlPermissionMapper;
import org.springframework.stereotype.Service;

/**
* @author q2893
* @description 针对表【t_role_url_permission(角色与菜单、接口权限的对应关系)】的数据库操作Service实现
* @createDate 2023-02-01 10:35:38
*/
@Service
public class RoleUrlPermissionServiceImpl extends ServiceImpl<RoleUrlPermissionMapper, RoleUrlPermission>
    implements RoleUrlPermissionService{

}




