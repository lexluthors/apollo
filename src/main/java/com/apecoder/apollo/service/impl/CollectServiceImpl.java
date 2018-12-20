package com.apecoder.apollo.service.impl;

import com.apecoder.apollo.domain.CollectEntity;
import com.apecoder.apollo.mapper.CollectPraiseMapper;
import com.apecoder.apollo.service.CollectService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl extends ServiceImpl<CollectPraiseMapper, CollectEntity> implements CollectService {

    @Autowired
    CollectPraiseMapper collectPraiseMapper;

    public List<CollectEntity> getCollectsByArticleIdAndUserId(Integer articleId, Integer userId, Page page) {
        LambdaQueryWrapper<CollectEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CollectEntity::getArticleId,articleId).eq(CollectEntity::getUserId,userId).eq(CollectEntity::getCollectStatus,"1").orderByDesc(CollectEntity::getUpdateDate);
        return collectPraiseMapper.selectPage(page,queryWrapper).getRecords();
    }

}
