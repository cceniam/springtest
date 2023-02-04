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
 * @TableName t_orderitem
 */
@TableName(value ="t_orderitem")
@Data
public class Orderitem implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "product_id")
    private Integer productId;

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

    /**
     * 
     */
    @TableField(value = "buycount")
    private Integer buycount;

    /**
     * 
     */
    @TableField(value = "sumprice")
    private BigDecimal sumprice;

    /**
     * 
     */
    @TableField(value = "order_id")
    private Integer orderId;

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
        Orderitem other = (Orderitem) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getProductname() == null ? other.getProductname() == null : this.getProductname().equals(other.getProductname()))
            && (this.getProductprice() == null ? other.getProductprice() == null : this.getProductprice().equals(other.getProductprice()))
            && (this.getProductimgsrc() == null ? other.getProductimgsrc() == null : this.getProductimgsrc().equals(other.getProductimgsrc()))
            && (this.getBuycount() == null ? other.getBuycount() == null : this.getBuycount().equals(other.getBuycount()))
            && (this.getSumprice() == null ? other.getSumprice() == null : this.getSumprice().equals(other.getSumprice()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getProductname() == null) ? 0 : getProductname().hashCode());
        result = prime * result + ((getProductprice() == null) ? 0 : getProductprice().hashCode());
        result = prime * result + ((getProductimgsrc() == null) ? 0 : getProductimgsrc().hashCode());
        result = prime * result + ((getBuycount() == null) ? 0 : getBuycount().hashCode());
        result = prime * result + ((getSumprice() == null) ? 0 : getSumprice().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", productId=").append(productId);
        sb.append(", productname=").append(productname);
        sb.append(", productprice=").append(productprice);
        sb.append(", productimgsrc=").append(productimgsrc);
        sb.append(", buycount=").append(buycount);
        sb.append(", sumprice=").append(sumprice);
        sb.append(", orderId=").append(orderId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}