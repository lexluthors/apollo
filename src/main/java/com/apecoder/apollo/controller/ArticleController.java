package com.apecoder.apollo.controller;

import com.apecoder.apollo.domain.ArticleBean;
import com.apecoder.apollo.domain.ArticleEntity;
import com.apecoder.apollo.domain.Result;
import com.apecoder.apollo.domain.UserBean;
import com.apecoder.apollo.repository.ArticleRepository;
import com.apecoder.apollo.repository.UserRepository;
import com.apecoder.apollo.service.impl.ArticleNewServiceImpl;
import com.apecoder.apollo.service.impl.ArticleServiceImpl;
import com.apecoder.apollo.utils.EntityCopyUtil;
import com.apecoder.apollo.utils.ResultUtil;
import com.apecoder.apollo.utils.TextUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(value = "ArticleController", description = "文章管理相关接口")
@RestController
public class ArticleController {

    @Autowired
    private ArticleServiceImpl articleService;
    @Autowired
    private ArticleNewServiceImpl articleNewService;

    private final static Logger logger = LoggerFactory.getLogger(ArticleController.class);


    /**
     * 新增一篇文章
     */
    @ApiOperation(value = "新增文章", notes = "新增文章接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "文章标题", required = true, dataType = "string"),
            @ApiImplicitParam(name = "des", value = "文章描述", required = false, dataType = "string"),
            @ApiImplicitParam(name = "tag", value = "文章标签", required = false, dataType = "string"),
            @ApiImplicitParam(name = "contributorId", value = "贡献者ID", required = true, dataType = "int"),
            @ApiImplicitParam(name = "category", value = "文章分类", required = true, dataType = "int"),
            @ApiImplicitParam(name = "sencondCategory", value = "文章子分类", required = false, dataType = "int"),
            @ApiImplicitParam(name = "link", value = "文章链接", required = true, dataType = "string"),
            @ApiImplicitParam(name = "coverImage", value = "文章封面图片的url", required = true, dataType = "string")
    })
    @PostMapping(value = "/add/article")
    public Result<ArticleBean> addArticle(@Valid ArticleBean articleBean, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        ArticleBean articleBean1 = articleService.getOne(Wrappers.query(new ArticleBean()).eq(ArticleBean::getLink,articleBean.getLink()));
        if (null != articleBean1) {
            return ResultUtil.error("该文章已经存在了");
        }
        //审核中0
        articleBean.setAuditSatus(0);
        logger.error("你妈的》》》》"+articleBean.getDate()+articleBean.getUpdateDate());
        return ResultUtil.success(articleService.save(articleBean));
    }
    /**
     * 新增一篇文章
     */
    @ApiOperation(value = "新增文章", notes = "新增文章接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "文章标题", required = true, dataType = "string"),
            @ApiImplicitParam(name = "des", value = "文章描述", required = false, dataType = "string"),
            @ApiImplicitParam(name = "tag", value = "文章标签", required = false, dataType = "string"),
            @ApiImplicitParam(name = "contributorId", value = "贡献者ID", required = true, dataType = "int"),
            @ApiImplicitParam(name = "category", value = "文章分类", required = true, dataType = "int"),
            @ApiImplicitParam(name = "sencondCategory", value = "文章子分类", required = false, dataType = "int"),
            @ApiImplicitParam(name = "link", value = "文章链接", required = true, dataType = "string"),
            @ApiImplicitParam(name = "coverImage", value = "文章封面图片的url", required = true, dataType = "string")
    })
    @PostMapping(value = "/add_article")
    public Result<ArticleEntity> addNewArticle(@Valid ArticleEntity articleBean, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        ArticleEntity articleBean1 = articleNewService.getOne(Wrappers.query(new ArticleEntity()).eq(ArticleEntity::getLink,articleBean.getLink()));
        if (null != articleBean1) {
            return ResultUtil.error("该文章已经存在了");
        }
        //审核中0
        articleBean.setAuditSatus(0);
        if(articleNewService.save(articleBean)){
            return ResultUtil.success(articleBean);
        }
        return ResultUtil.error("该文章已经存在了");
    }

    //获取一个文章
    @PostMapping(value = "/get/article")
    public Result<ArticleBean> getArticleById(@RequestParam("article_id") Integer article_id) {
        ArticleBean articleBean = articleService.getById(article_id);
        if (null == articleBean) {
            //未找到该文章
            return ResultUtil.error(ResultUtil.ERROR_CODE, "未找到该文章");
        }
        return ResultUtil.success(articleBean);
    }

    //更新一篇文章
    @ApiOperation(value = "新增文章", notes = "新增文章接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章id", required = true, dataType = "int"),
            @ApiImplicitParam(name = "title", value = "文章标题", required = true, dataType = "string"),
            @ApiImplicitParam(name = "des", value = "文章描述", required = false, dataType = "string"),
            @ApiImplicitParam(name = "tag", value = "文章标签", required = false, dataType = "string"),
            @ApiImplicitParam(name = "contributorId", value = "发布者ID", required = true, dataType = "int"),
            @ApiImplicitParam(name = "category", value = "文章分类", required = true, dataType = "int"),
            @ApiImplicitParam(name = "sencondCategory", value = "文章子分类", required = false, dataType = "int"),
            @ApiImplicitParam(name = "link", value = "文章链接", required = true, dataType = "string"),
            @ApiImplicitParam(name = "coverImage", value = "文章封面图片的url", required = true, dataType = "string")
    })
    @PostMapping(value = "/article/update")
    public Result<ArticleBean> articleUpdate(@Valid ArticleBean articleBean, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ResultUtil.error(ResultUtil.ERROR_CODE, bindingResult.getFieldError().getDefaultMessage());
        }
        ArticleBean articleBean1 = articleService.getById(articleBean.getId());
        if (null != articleBean1) {
            if (articleBean.getContributorId().equals(articleBean1.getContributorId())) {
                EntityCopyUtil.beanCopyWithIngore(articleBean, articleBean1, "contributorId");
                if(articleService.save(articleBean1)){
                    return ResultUtil.success(articleBean1);
                }
            }
            return ResultUtil.error("你无权修改此文章");
        }
        return ResultUtil.error("未找到该文章");
    }

//    @PostMapping(value = "/get_article_bylink")
//    public Result<ArticleBean> articleByLink(@RequestParam("link") String article_link) {
//        ArticleBean articleBean = articleService.findArticleBeanByLink(article_link);
//        if (null != articleBean) {
//            return ResultUtil.success(articleBean);
//        }
//        return ResultUtil.error("未找到该文章");
//    }

    @ApiOperation(value = "根据category获取文章列表")
    @PostMapping(value = "/articles_by_category")
    public Result<List<ArticleBean>> articleByCategory(@RequestParam("category") Integer category, @RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        List<ArticleBean> articleBeans = articleService.selectListByCategory(category, page, pageSize);
        return ResultUtil.success(articleBeans);
    }

}
