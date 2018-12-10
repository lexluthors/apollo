package com.apecoder.apollo.domain;

import com.alibaba.fastjson.annotation.JSONField;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class ArticleBean implements Serializable {
    //文章id
    @Id
    @GeneratedValue
    private Integer id;

    private Integer contributorId;

    //描述
    private String des;

    //创建时间
    @CreatedDate
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    //更新时间
    @LastModifiedDate
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    //封面图
    private String coverImage;

    @NotNull(message = "文章标题必传")
    private String title;
    @NotNull(message = "文章连接必传")
    private String link;

    private String tag;//文章标签

    @NotNull(message="分类不能为空！")
    private Integer category;//文章分类（Android、iOS、Java等）0安卓，1 ios,2 java,3 PHP

    private Integer sencondCategory;//文章二级分类(开源库0、资讯1、资料2等)
    private Integer audittSatus;//审核状态(审核中0、通过1、未通过2)



    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getContributorId() {
        return contributorId;
    }

    public void setContributorId(Integer contributorId) {
        this.contributorId = contributorId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public int getSencondCategory() {
        return sencondCategory;
    }

    public void setSencondCategory(int sencondCategory) {
        this.sencondCategory = sencondCategory;
    }

    public int getAudittSatus() {
        return audittSatus;
    }

    public void setAudittSatus(int audittSatus) {
        this.audittSatus = audittSatus;
    }
}
