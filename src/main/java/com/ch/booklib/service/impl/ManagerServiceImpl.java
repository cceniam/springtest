package com.ch.booklib.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ch.booklib.entity.Manager;
import com.ch.booklib.service.ManagerService;
import com.ch.booklib.mapper.ManagerMapper;
import org.springframework.stereotype.Service;

/**
* @author q2893
* @description 针对表【t_manager(内部员工信息)】的数据库操作Service实现
* @createDate 2023-02-01 10:35:38
*/
@Service
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager>
    implements ManagerService{

}




