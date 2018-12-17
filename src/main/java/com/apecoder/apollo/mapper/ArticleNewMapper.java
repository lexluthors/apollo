package com.apecoder.apollo.mapper;

import com.apecoder.apollo.domain.ArticleEntity;
import com.apecoder.apollo.domain.ArticleItemVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @description 
 * @author Allen
 * @date 2018/12/13 13:09
 */
public interface ArticleNewMapper extends BaseMapper<ArticleEntity> {
    /**
     * 获取带有用户信息的文章列表
     * @param page
     * @return
     */
//    @Select("SELECT * FROM article_entity b LEFT JOIN user_bean as u ON  u.id=b.contributor_id")
//    public List<Map> selectArticlesByBandUser(Page page);


    public List<ArticleItemVo> selectArticlesByBandUser(Page page);

}
