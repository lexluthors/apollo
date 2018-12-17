package com.apecoder.apollo.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 2018/12/13 14:14
 * @description TODO
 * @date Allen
 */
@Getter
@Setter
public class ArticleItemVo extends BaseEntity {

    private Long contributorId;

    //描述
    private String des;

    //封面图
    private String coverImage;

    private String title;
    private String link;

    private String tag;//文章标签
    /**
     * 文章分类（Android、iOS、Java等）0安卓，1 ios,2 java,3 PHP
     */
    private Integer category;
    //文章二级分类(开源库0、资讯1、资料2等)
    private Integer sencondCategory;
    //审核状态(审核中0、通过1、未通过2)
    private Integer auditSatus;
    private UserBean user;

}
