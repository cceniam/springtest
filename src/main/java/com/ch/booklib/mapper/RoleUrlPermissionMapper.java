package com.ch.booklib.mapper;

import com.ch.booklib.entity.RoleUrlPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper

/**
* @author q2893
* @description 针对表【t_role_url_permission(角色与菜单、接口权限的对应关系)】的数据库操作Mapper
* @createDate 2023-02-01 10:35:38
* @Entity com.ch.booklib.domain.RoleUrlPermission
*/
public interface RoleUrlPermissionMapper extends BaseMapper<RoleUrlPermission> {

}




