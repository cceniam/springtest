package com.woniuxy.qiantai.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author woniumrwang
 * @since 2023-01-06 02:44:35
 */
@Getter
@Setter
@TableName("t_address")
public class Address {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("province")
    private String province;

    @TableField("city")
    private String city;

    @TableField("area")
    private String area;

    @TableField("detailAddress")
    private String detailAddress;

    @TableField("tel")
    private String tel;

    @TableField("reciver")
    private String reciver;

    @TableField("emailCode")
    private String emailCode;

    @TableField("userId")
    private Long userId;

    @TableField("status")
    private String status;

    @TableField("isDefault")
    private String isDefault;


}
