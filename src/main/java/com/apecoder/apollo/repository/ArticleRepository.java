package com.apecoder.apollo.repository;

import com.apecoder.apollo.domain.ArticleBean;
import com.apecoder.apollo.domain.UserBean;

import javax.crypto.SecretKey;
import java.util.List;

public interface ArticleRepository extends SecretKey {

    //通过id来查询
    public ArticleBean findArticleBeanById(int article_id);

    ArticleBean findArticleBeanByLink( String article_link);

    public List<ArticleBean> findArticleBeansByCategory(int category);

}
