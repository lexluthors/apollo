package com.apecoder.apollo.controller;

import com.apecoder.apollo.domain.ArticleEntity;
import com.apecoder.apollo.domain.CommentArticleEntity;
import com.apecoder.apollo.domain.Result;
import com.apecoder.apollo.domain.UserBean;
import com.apecoder.apollo.service.impl.ArticleNewServiceImpl;
import com.apecoder.apollo.service.impl.CommentServiceImpl;
import com.apecoder.apollo.service.impl.UserServiceImpl;
import com.apecoder.apollo.utils.ResultUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(value = "CommentController", description = "文章评论相关接口")
@RestController
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;
    @Autowired
    private ArticleNewServiceImpl articleService;
    @Autowired
    private UserServiceImpl userService;

    private final static Logger logger = LoggerFactory.getLogger(CommentController.class);


    /**
     * 新增一篇文章
     */
    @ApiOperation(value = "评论文章", notes = "评论文章以及评论用户接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId", value = "文章id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "fromId", value = "评论者id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "toId", value = "被评论者id", required = false, dataType = "Long"),
            @ApiImplicitParam(name = "content", value = "评论内容", required = true, dataType = "string"),
            @ApiImplicitParam(name = "fromAvatar", value = "评论者头像", required = false, dataType = "string"),
            @ApiImplicitParam(name = "toAvatar", value = "被评论者头像", required = false, dataType = "string"),
            @ApiImplicitParam(name = "fromNickName", value = "评论者昵称", required = false, dataType = "string"),
            @ApiImplicitParam(name = "toNickName", value = "被评论者昵称", required = false, dataType = "string")
    })
    @PostMapping(value = "/comment")
    public Result<CommentArticleEntity> addComment(@Valid CommentArticleEntity commentArticleEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        ArticleEntity articleBean = articleService.getById(commentArticleEntity.getArticleId());
        UserBean userBean = userService.getById(commentArticleEntity.getFromId());
        if (null == userBean) {
            return ResultUtil.error("该用户不存在");
        }
        if (null == articleBean) {
            return ResultUtil.error("该文章不存在");
        }
        if(StringUtils.isNotEmpty(String.valueOf(commentArticleEntity.getToId()))){
            //如果被评论者id不为空，就查询被评论者信息
            UserBean toUser = userService.getById(commentArticleEntity.getToId());
            commentArticleEntity.setToAvatar(toUser.getAvatar());
            commentArticleEntity.setToNickName(toUser.getNickName());
        }
        commentArticleEntity.setFromAvatar(userBean.getAvatar());
        commentArticleEntity.setFromNickName(userBean.getNickName());
        if(commentService.save(commentArticleEntity)){
            return ResultUtil.success(commentArticleEntity);
        }
        return ResultUtil.error("评论失败");
    }

    @ApiOperation(value = "获取文章评论列表")
    @PostMapping(value = "/getCommentList")
    public Result<CommentArticleEntity> getCommentList(@RequestParam("articleId") Integer articleId, @RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        List<CommentArticleEntity> commentList = commentService.getCommentsByArticleId(articleId,new Page(page,pageSize));
        return ResultUtil.success(commentList);
    }

}
