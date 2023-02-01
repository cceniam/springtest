package com.ch.booklib.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ch.booklib.entity.Item;
import com.ch.booklib.service.ItemService;
import com.ch.booklib.mapper.ItemMapper;
import org.springframework.stereotype.Service;

/**
* @author q2893
* @description 针对表【t_item】的数据库操作Service实现
* @createDate 2023-02-01 10:35:38
*/
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item>
    implements ItemService{

}




