package com.apecoder.apollo.service.impl;

import com.apecoder.apollo.domain.CommentArticleEntity;
import com.apecoder.apollo.mapper.CommentMapper;
import com.apecoder.apollo.service.CommentService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, CommentArticleEntity> implements CommentService {

    @Autowired
    CommentMapper articleMapper;

    public List<CommentArticleEntity> getCommentsByArticleId(Integer articleId, Page page) {
        IPage<CommentArticleEntity> iPage = articleMapper.selectPage(page,
                Wrappers.query(new CommentArticleEntity()).eq(CommentArticleEntity::getArticleId, articleId).orderByDesc(CommentArticleEntity::getUpdateDate)
        );
        return iPage.getRecords();
    }

}
