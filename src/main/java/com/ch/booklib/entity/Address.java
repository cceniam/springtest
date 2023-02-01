package com.ch.booklib.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName t_address
 */
@TableName(value ="t_address")
@Data
public class Address implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "province")
    private String province;

    /**
     * 
     */
    @TableField(value = "city")
    private String city;

    /**
     * 
     */
    @TableField(value = "area")
    private String area;

    /**
     * 
     */
    @TableField(value = "detailAddress")
    private String detailaddress;

    /**
     * 
     */
    @TableField(value = "tel")
    private String tel;

    /**
     * 
     */
    @TableField(value = "reciver")
    private String reciver;

    /**
     * 
     */
    @TableField(value = "emailCode")
    private String emailcode;

    /**
     * 
     */
    @TableField(value = "userId")
    private Long userid;

    /**
     * 
     */
    @TableField(value = "status")
    private String status;

    /**
     * 
     */
    @TableField(value = "isDefault")
    private String isdefault;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Address other = (Address) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getArea() == null ? other.getArea() == null : this.getArea().equals(other.getArea()))
            && (this.getDetailaddress() == null ? other.getDetailaddress() == null : this.getDetailaddress().equals(other.getDetailaddress()))
            && (this.getTel() == null ? other.getTel() == null : this.getTel().equals(other.getTel()))
            && (this.getReciver() == null ? other.getReciver() == null : this.getReciver().equals(other.getReciver()))
            && (this.getEmailcode() == null ? other.getEmailcode() == null : this.getEmailcode().equals(other.getEmailcode()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getIsdefault() == null ? other.getIsdefault() == null : this.getIsdefault().equals(other.getIsdefault()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getArea() == null) ? 0 : getArea().hashCode());
        result = prime * result + ((getDetailaddress() == null) ? 0 : getDetailaddress().hashCode());
        result = prime * result + ((getTel() == null) ? 0 : getTel().hashCode());
        result = prime * result + ((getReciver() == null) ? 0 : getReciver().hashCode());
        result = prime * result + ((getEmailcode() == null) ? 0 : getEmailcode().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getIsdefault() == null) ? 0 : getIsdefault().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", area=").append(area);
        sb.append(", detailaddress=").append(detailaddress);
        sb.append(", tel=").append(tel);
        sb.append(", reciver=").append(reciver);
        sb.append(", emailcode=").append(emailcode);
        sb.append(", userid=").append(userid);
        sb.append(", status=").append(status);
        sb.append(", isdefault=").append(isdefault);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}