package com.apecoder.apollo.service.impl;

import com.apecoder.apollo.domain.ArticleBean;
import com.apecoder.apollo.domain.ArticleEntity;
import com.apecoder.apollo.mapper.ArticleMapper;
import com.apecoder.apollo.mapper.ArticleNewMapper;
import com.apecoder.apollo.service.ArticleNewService;
import com.apecoder.apollo.service.ArticleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleNewServiceImpl extends ServiceImpl<ArticleNewMapper, ArticleEntity> implements ArticleNewService {

    @Autowired
    ArticleNewMapper articleMapper;

    public  List<ArticleEntity>  selectListByCategory(Integer category,Integer page,Integer pageSize) {
        IPage<ArticleEntity> iPage = articleMapper.selectPage(new Page<ArticleEntity>(page,pageSize),
                Wrappers.query(new ArticleEntity()).eq(ArticleEntity::getCategory,category)
        );
        return  iPage.getRecords();
    }

    public int insert(ArticleEntity articleBean){
        return articleMapper.insert(articleBean);
    }
}
