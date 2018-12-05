package com.apecoder.apollo.repository;

import com.apecoder.apollo.domain.ArticleBean;
import com.apecoder.apollo.domain.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<ArticleBean,Integer> {

    //通过id来查询
    public ArticleBean findArticleBeanById(int article_id);

    ArticleBean findArticleBeanByLink( String article_link);

    public List<ArticleBean> findArticleBeansByCategory(int category);

}
