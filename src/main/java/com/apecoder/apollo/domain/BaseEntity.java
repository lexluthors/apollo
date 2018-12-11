package com.apecoder.apollo.domain;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * @author 2018/12/11 17:09
 * @description TODO
 * @date Allen
 */
public class BaseEntity <T extends Model> extends Model<T> {
    private Long id;

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
}
