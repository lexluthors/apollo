package com.apecoder.apollo.controller;

import com.apecoder.apollo.domain.CollectEntity;
import com.apecoder.apollo.domain.Result;
import com.apecoder.apollo.service.impl.ArticleNewServiceImpl;
import com.apecoder.apollo.service.impl.CollectServiceImpl;
import com.apecoder.apollo.service.impl.UserServiceImpl;
import com.apecoder.apollo.utils.ResultUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Api(value = "CollectPraiseController", description = "文章点赞和收藏相关接口")
@RestController
public class CollectPraiseController {

    @Autowired
    private CollectServiceImpl collectService;
    @Autowired
    private ArticleNewServiceImpl articleService;
    @Autowired
    private UserServiceImpl userService;

    private final static Logger logger = LoggerFactory.getLogger(CollectPraiseController.class);


    /**
     * 新增一篇文章
     */
    @ApiOperation(value = "获取文章收藏列表", notes = "获取文章收藏列表接口")
    @PostMapping(value = "/getCollectList")
    public Result getCollectList(@RequestParam(value = "articleId") Integer articleId,@RequestParam(value = "userId") Integer userId, @RequestParam(value = "page") Integer page,@RequestParam(value = "pageSize") Integer pageSize) {
        List<CollectEntity> collectEntities =collectService.getCollectsByArticleIdAndUserId(articleId,userId,new Page(page,pageSize));
        if(collectEntities.size()>0){
            List<Long> userIds = collectEntities.stream().map(CollectEntity::getUserId).collect(Collectors.toList());
            return ResultUtil.success(userService.selectUsers(userIds));
        }
        return ResultUtil.success(collectEntities);
    }

}
