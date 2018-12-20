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

    public List<ArticleEntity> selectListByCategory(Integer category, Page page) {
        IPage<ArticleEntity> iPage = articleMapper.selectPage(page,
                Wrappers.query(new ArticleEntity()).eq(ArticleEntity::getCategory, category).orderByDesc(ArticleEntity::getUpdateDate)
        );
        return iPage.getRecords();
    }

    /**
     * 获取所有的文章列表，按时间倒叙排序
     * @param page
     * @param pageSize
     * @return
     */
    public List<ArticleEntity> getAllArticleList(Integer page, Integer pageSize) {
        QueryWrapper<ArticleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("update_date");
        IPage<ArticleEntity> iPage = articleMapper.selectPage(new Page<ArticleEntity>(page, pageSize), queryWrapper);
        return iPage.getRecords();
    }

    public int insert(ArticleEntity articleBean) {
        return articleMapper.insert(articleBean);
    }

    public List<ArticleItemVo> getListArticles(Integer category,Page page) {
        return articleMapper.selectArticlesByBandUser(category,page);
    }

    public List<ArticleEntity> selectArticles(Page page) {
        IPage<ArticleEntity> iPage = articleMapper.selectPage(page,
                Wrappers.query(new ArticleEntity())
        );
        return iPage.getRecords();
    }

    public ArticleEntity getOneByLink(String link){
        QueryWrapper<ArticleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("link",link);
        return articleMapper.selectOne(queryWrapper);
    }
}
