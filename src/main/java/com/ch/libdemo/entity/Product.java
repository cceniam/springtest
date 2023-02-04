package com.ch.libdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 
 * @TableName t_product
 */
@TableName(value ="t_product")
@Data
public class Product implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "productname")
    private String productname;

    /**
     * 
     */
    @TableField(value = "productprice")
    private BigDecimal productprice;

    /**
     * 
     */
    @TableField(value = "productimgsrc")
    private String productimgsrc;

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
        Product other = (Product) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProductname() == null ? other.getProductname() == null : this.getProductname().equals(other.getProductname()))
            && (this.getProductprice() == null ? other.getProductprice() == null : this.getProductprice().equals(other.getProductprice()))
            && (this.getProductimgsrc() == null ? other.getProductimgsrc() == null : this.getProductimgsrc().equals(other.getProductimgsrc()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProductname() == null) ? 0 : getProductname().hashCode());
        result = prime * result + ((getProductprice() == null) ? 0 : getProductprice().hashCode());
        result = prime * result + ((getProductimgsrc() == null) ? 0 : getProductimgsrc().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", productname=").append(productname);
        sb.append(", productprice=").append(productprice);
        sb.append(", productimgsrc=").append(productimgsrc);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}