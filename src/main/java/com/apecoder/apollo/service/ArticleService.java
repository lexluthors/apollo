package com.apecoder.apollo.service;

import com.apecoder.apollo.domain.ArticleBean;
import com.apecoder.apollo.domain.UserBean;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ArticleService extends IService<ArticleBean> {

    public List<ArticleBean> selectListByNickName(String nickName);
}
