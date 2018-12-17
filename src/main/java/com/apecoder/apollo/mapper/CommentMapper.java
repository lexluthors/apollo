package com.apecoder.apollo.mapper;

import com.apecoder.apollo.domain.CommentArticleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @description 
 * @author Allen
 * @date 2018/12/13 13:09
 */
public interface CommentMapper extends BaseMapper<CommentArticleEntity> {
    /**
     * 获取带有用户信息的文章列表
     * @param page
     * @return
     */
//    @Select("SELECT * FROM article_entity b LEFT JOIN user_bean as u ON  u.id=b.contributor_id")
//    public List<Map> selectArticlesByBandUser(Page page);


//    public List<CommentArticleEntity> get(Page page);

}
