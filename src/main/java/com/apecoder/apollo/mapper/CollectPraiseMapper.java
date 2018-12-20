package com.apecoder.apollo.mapper;

import com.apecoder.apollo.domain.CollectEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @description 
 * @author Allen
 * @date 2018/12/13 13:09
 */
public interface CollectPraiseMapper extends BaseMapper<CollectEntity> {
    /**
     * 获取文章的点赞列表列表，根据文章id
     * @param page
     * @return
     */
}
