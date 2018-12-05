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

    private Integer contributor_id;

    //描述
    private String des;

    //创建时间
    @CreatedDate
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    //更新时间
    @LastModifiedDate
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date update_date;

    //封面图
    private String cover_image;

    @NotNull(message = "文章标题必传")
    private String title;
    @NotNull(message = "文章连接必传")
    private String link;

    private String tag;//文章标签

    @NotNull(message="分类不能为空！")
    private int category;//文章分类（Android、iOS、Java等）0，1,2,3

    private int sencond_category;//文章二级分类(开源库0、资讯1、资料2等)

    public Integer getContributor_id() {
        return contributor_id;
    }

    public void setContributor_id(Integer contributor_id) {
        this.contributor_id = contributor_id;
    }

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

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
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


    public int getSencond_category() {
        return sencond_category;
    }

    public void setSencond_category(int sencond_category) {
        this.sencond_category = sencond_category;
    }
}
