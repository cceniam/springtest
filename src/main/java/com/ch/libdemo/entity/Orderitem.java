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
    private Integer product_id;

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
    private Integer order_id;

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
            && (this.getProduct_id() == null ? other.getProduct_id() == null : this.getProduct_id().equals(other.getProduct_id()))
            && (this.getProductname() == null ? other.getProductname() == null : this.getProductname().equals(other.getProductname()))
            && (this.getProductprice() == null ? other.getProductprice() == null : this.getProductprice().equals(other.getProductprice()))
            && (this.getProductimgsrc() == null ? other.getProductimgsrc() == null : this.getProductimgsrc().equals(other.getProductimgsrc()))
            && (this.getBuycount() == null ? other.getBuycount() == null : this.getBuycount().equals(other.getBuycount()))
            && (this.getSumprice() == null ? other.getSumprice() == null : this.getSumprice().equals(other.getSumprice()))
            && (this.getOrder_id() == null ? other.getOrder_id() == null : this.getOrder_id().equals(other.getOrder_id()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProduct_id() == null) ? 0 : getProduct_id().hashCode());
        result = prime * result + ((getProductname() == null) ? 0 : getProductname().hashCode());
        result = prime * result + ((getProductprice() == null) ? 0 : getProductprice().hashCode());
        result = prime * result + ((getProductimgsrc() == null) ? 0 : getProductimgsrc().hashCode());
        result = prime * result + ((getBuycount() == null) ? 0 : getBuycount().hashCode());
        result = prime * result + ((getSumprice() == null) ? 0 : getSumprice().hashCode());
        result = prime * result + ((getOrder_id() == null) ? 0 : getOrder_id().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", product_id=").append(product_id);
        sb.append(", productname=").append(productname);
        sb.append(", productprice=").append(productprice);
        sb.append(", productimgsrc=").append(productimgsrc);
        sb.append(", buycount=").append(buycount);
        sb.append(", sumprice=").append(sumprice);
        sb.append(", order_id=").append(order_id);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}