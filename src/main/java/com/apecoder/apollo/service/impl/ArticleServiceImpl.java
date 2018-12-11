package com.apecoder.apollo.service.impl;

import com.apecoder.apollo.domain.ArticleBean;
import com.apecoder.apollo.domain.UserBean;
import com.apecoder.apollo.mapper.ArticleMapper;
import com.apecoder.apollo.mapper.UserMapper;
import com.apecoder.apollo.service.ArticleService;
import com.apecoder.apollo.service.UserService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, ArticleBean> implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    public  List<ArticleBean>  selectListByCategory(Integer category,Integer page,Integer pageSize) {
        IPage<ArticleBean> iPage = articleMapper.selectPage(new Page<ArticleBean>(page,pageSize),
                Wrappers.query(new ArticleBean()).eq(ArticleBean::getCategory,category)
        );
        return  iPage.getRecords();
    }

    public int insert(ArticleBean articleBean){
        return articleMapper.insert(articleBean);
    }
}
