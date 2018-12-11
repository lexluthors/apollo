package com.apecoder.apollo.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class UserBean extends BaseEntity {
//    @TableId(type = IdType.AUTO)
//    private Integer id;

    private Integer age;
    private Integer userLevel;//管理员是0；普通用户是1，后续会开通vip功能
    private Double money;
    private Float income;
    @JSONField(serialize = false)
    private String cupSize;
    private String des;

    private String name;
    private String nickName;
    private String hobby;
    @TableField(fill = FieldFill.INSERT)
    private Date date;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateDate;

    @JSONField(serialize = false)
    @NotNull(message = "密码必传")
    private String password;
    @NotNull(message = "手机号必传")
    private String phone;

    private String gender;


}
