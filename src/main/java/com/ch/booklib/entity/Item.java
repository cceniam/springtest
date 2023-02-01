package com.ch.booklib.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName t_item
 */
@TableName(value ="t_item")
@Data
public class Item implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    @TableField(value = "bookId")
    private Long bookid;

    /**
     * 
     */
    @TableField(value = "bookName")
    private String bookname;

    /**
     * 
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 
     */
    @TableField(value = "bcount")
    private Integer bcount;

    /**
     * 
     */
    @TableField(value = "sumprice")
    private BigDecimal sumprice;

    /**
     * 
     */
    @TableField(value = "orderId")
    private Long orderid;

    /**
     * 
     */
    @TableField(value = "createtime")
    private Date createtime;

    /**
     * 
     */
    @TableField(value = "state")
    private Integer state;

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
        Item other = (Item) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBookid() == null ? other.getBookid() == null : this.getBookid().equals(other.getBookid()))
            && (this.getBookname() == null ? other.getBookname() == null : this.getBookname().equals(other.getBookname()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getBcount() == null ? other.getBcount() == null : this.getBcount().equals(other.getBcount()))
            && (this.getSumprice() == null ? other.getSumprice() == null : this.getSumprice().equals(other.getSumprice()))
            && (this.getOrderid() == null ? other.getOrderid() == null : this.getOrderid().equals(other.getOrderid()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBookid() == null) ? 0 : getBookid().hashCode());
        result = prime * result + ((getBookname() == null) ? 0 : getBookname().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getBcount() == null) ? 0 : getBcount().hashCode());
        result = prime * result + ((getSumprice() == null) ? 0 : getSumprice().hashCode());
        result = prime * result + ((getOrderid() == null) ? 0 : getOrderid().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", bookid=").append(bookid);
        sb.append(", bookname=").append(bookname);
        sb.append(", price=").append(price);
        sb.append(", bcount=").append(bcount);
        sb.append(", sumprice=").append(sumprice);
        sb.append(", orderid=").append(orderid);
        sb.append(", createtime=").append(createtime);
        sb.append(", state=").append(state);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}