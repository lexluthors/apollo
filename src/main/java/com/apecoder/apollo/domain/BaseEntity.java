package com.apecoder.apollo.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 2018/12/11 17:09
 * @description TODO
 * @date Allen
 */
public class BaseEntity <T extends Model> extends Model<T> {
    private Long id;
    @TableField(fill = FieldFill.INSERT)
    private Date date;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateDate;
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
