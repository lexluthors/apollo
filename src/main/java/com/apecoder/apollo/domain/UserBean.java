package com.apecoder.apollo.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

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


    @JSONField(serialize = false)
//    @NotNull(message = "密码必传")
    private String password;
//    @NotNull(message = "手机号必传")
    private String phone;

    private String gender;
    private String avatar;


}
