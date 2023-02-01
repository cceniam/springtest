package com.ch.booklib.mapper;

import com.ch.booklib.entity.Item;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper

/**
* @author q2893
* @description 针对表【t_item】的数据库操作Mapper
* @createDate 2023-02-01 10:35:38
* @Entity com.ch.booklib.domain.Item
*/
public interface ItemMapper extends BaseMapper<Item> {

}




