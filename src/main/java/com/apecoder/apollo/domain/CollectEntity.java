package com.apecoder.apollo.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author 2018/12/18 15:20
 * @description TODO 收藏和点赞列表
 * @date Allen
 */
@Data
public class CollectEntity extends BaseEntity{
    /**
     * 收藏的哪篇文章  文章id为唯一的，对应多个用户，很多用户收藏
     */
    @NotNull(message = "文章id必填")
    private Long articleId;

    /**
     * 谁收藏的
     */
    @NotNull(message = "用户id必填")
    private Long userId;

    //收藏状态 0未收藏，1已收藏
    private Integer collectStatus;
    //点赞状态，0未点赞，1已点赞
    private Integer praiseStatus;
}
