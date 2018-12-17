package com.apecoder.apollo.service.impl;

import com.apecoder.apollo.domain.ArticleEntity;
import com.apecoder.apollo.domain.ArticleItemVo;
import com.apecoder.apollo.mapper.ArticleNewMapper;
import com.apecoder.apollo.service.ArticleNewService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    public  List<ArticleEntity>  selectListById(Integer category,Integer page,Integer pageSize) {
        QueryWrapper<ArticleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",category);
        IPage<ArticleEntity> iPage = articleMapper.selectPage(new Page<ArticleEntity>(page,pageSize),queryWrapper);
        return  iPage.getRecords();
    }

    public int insert(ArticleEntity articleBean){
        return articleMapper.insert(articleBean);
    }

    public List<ArticleItemVo> getListArticles(Page page){
        return articleMapper.selectArticlesByBandUser(page);
    }

    public  List<ArticleEntity>  selectArticles(Page page) {
        IPage<ArticleEntity> iPage = articleMapper.selectPage(page,
                Wrappers.query(new ArticleEntity())
        );
        return  iPage.getRecords();
    }
}
