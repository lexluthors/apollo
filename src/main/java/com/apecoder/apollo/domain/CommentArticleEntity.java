package com.apecoder.apollo.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class CommentArticleEntity extends BaseEntity {
    //评论id在base中
    @NotNull(message = "文章id必填")
    private Long articleId;

    /**
     * 评论内容
     */
    @NotNull(message = "评论内容必填")
    private String content;
    /**
     * 评论者用户id
     */
    @NotNull(message = "评论者id必填")
    private Long fromId;
    //评论目标用户id
    private Long toId;

    private String fromAvatar;//评论者头像
    private String toAvatar;//被评论者头像
    private String fromNickName;//评论者昵称
    private String toNickName;//被评论者昵称

}
