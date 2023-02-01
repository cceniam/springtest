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
 * @TableName t_book
 */
@TableName(value ="t_book")
@Data
public class Book implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    @TableField(value = "name")
    private String name;

    /**
     * 
     */
    @TableField(value = "typeId")
    private Integer typeid;

    /**
     * 
     */
    @TableField(value = "provider")
    private String provider;

    /**
     * 
     */
    @TableField(value = "author")
    private String author;

    /**
     * 
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 
     */
    @TableField(value = "detail")
    private String detail;

    /**
     * 
     */
    @TableField(value = "imgsrc")
    private String imgsrc;

    /**
     * 
     */
    @TableField(value = "collectioncount")
    private Integer collectioncount;

    /**
     * 
     */
    @TableField(value = "storecount")
    private Integer storecount;

    /**
     * 
     */
    @TableField(value = "buycount")
    private Integer buycount;

    /**
     * 点击量
     */
    @TableField(value = "readcount")
    private Integer readcount;

    /**
     * 
     */
    @TableField(value = "createtime")
    private Date createtime;

    /**
     * 
     */
    @TableField(value = "updatetime")
    private Date updatetime;

    /**
     * 
     */
    @TableField(value = "state")
    private Integer state;

    /**
     * 
     */
    @TableField(value = "version")
    private Integer version;

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
        Book other = (Book) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getTypeid() == null ? other.getTypeid() == null : this.getTypeid().equals(other.getTypeid()))
            && (this.getProvider() == null ? other.getProvider() == null : this.getProvider().equals(other.getProvider()))
            && (this.getAuthor() == null ? other.getAuthor() == null : this.getAuthor().equals(other.getAuthor()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getDetail() == null ? other.getDetail() == null : this.getDetail().equals(other.getDetail()))
            && (this.getImgsrc() == null ? other.getImgsrc() == null : this.getImgsrc().equals(other.getImgsrc()))
            && (this.getCollectioncount() == null ? other.getCollectioncount() == null : this.getCollectioncount().equals(other.getCollectioncount()))
            && (this.getStorecount() == null ? other.getStorecount() == null : this.getStorecount().equals(other.getStorecount()))
            && (this.getBuycount() == null ? other.getBuycount() == null : this.getBuycount().equals(other.getBuycount()))
            && (this.getReadcount() == null ? other.getReadcount() == null : this.getReadcount().equals(other.getReadcount()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getTypeid() == null) ? 0 : getTypeid().hashCode());
        result = prime * result + ((getProvider() == null) ? 0 : getProvider().hashCode());
        result = prime * result + ((getAuthor() == null) ? 0 : getAuthor().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getDetail() == null) ? 0 : getDetail().hashCode());
        result = prime * result + ((getImgsrc() == null) ? 0 : getImgsrc().hashCode());
        result = prime * result + ((getCollectioncount() == null) ? 0 : getCollectioncount().hashCode());
        result = prime * result + ((getStorecount() == null) ? 0 : getStorecount().hashCode());
        result = prime * result + ((getBuycount() == null) ? 0 : getBuycount().hashCode());
        result = prime * result + ((getReadcount() == null) ? 0 : getReadcount().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", typeid=").append(typeid);
        sb.append(", provider=").append(provider);
        sb.append(", author=").append(author);
        sb.append(", price=").append(price);
        sb.append(", detail=").append(detail);
        sb.append(", imgsrc=").append(imgsrc);
        sb.append(", collectioncount=").append(collectioncount);
        sb.append(", storecount=").append(storecount);
        sb.append(", buycount=").append(buycount);
        sb.append(", readcount=").append(readcount);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", state=").append(state);
        sb.append(", version=").append(version);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}