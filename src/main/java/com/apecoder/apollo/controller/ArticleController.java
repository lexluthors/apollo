package com.apecoder.apollo.controller;

import com.apecoder.apollo.domain.ArticleEntity;
import com.apecoder.apollo.domain.ArticleItemVo;
import com.apecoder.apollo.domain.Result;
import com.apecoder.apollo.service.impl.ArticleNewServiceImpl;
import com.apecoder.apollo.service.impl.UserServiceImpl;
import com.apecoder.apollo.utils.EntityCopyUtil;
import com.apecoder.apollo.utils.ResultUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "ArticleController", description = "文章管理相关接口")
@RestController
public class ArticleController {

    @Autowired
    private ArticleNewServiceImpl articleNewService;
    @Autowired
    private UserServiceImpl userService;

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
    @PostMapping(value = "/addArticle")
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
    @PostMapping(value = "/getArticle")
    public Result<ArticleEntity> getArticleById(@RequestParam("article_id") Long article_id) {
        ArticleEntity articleBean = articleNewService.getById(article_id);
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
    @PostMapping(value = "/updateArticle")
    public Result<ArticleEntity> articleUpdate(@Valid ArticleEntity articleBean, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ResultUtil.error(ResultUtil.ERROR_CODE, bindingResult.getFieldError().getDefaultMessage());
        }
        ArticleEntity articleBean1 = articleNewService.getById(articleBean.getId());
        if (null != articleBean1) {
            if (articleBean.getContributorId().equals(articleBean1.getContributorId())) {
                EntityCopyUtil.beanCopyWithIngore(articleBean, articleBean1, "contributorId");
                if(articleNewService.save(articleBean1)){
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
    @PostMapping(value = "/articlesByCategory")
    public Result<List<ArticleEntity>> articleByCategory(@RequestParam("category") Integer category, @RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        List<ArticleEntity> articleBeans = articleNewService.selectListByCategory(category, page, pageSize);
        return ResultUtil.success(articleBeans);
    }

    @ApiOperation(value = " 获取文章列表，带有用户信息的列表")
    @PostMapping(value = "/articles")
    public Result<List<ArticleItemVo>> getListArticles(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        List<ArticleItemVo> articleBeans = articleNewService.getListArticles(new Page(page,pageSize));
        return ResultUtil.success(articleBeans);
    }

//    @ApiOperation(value = " 获取文章列表，带有用户信息的列表")
//    @PostMapping(value = "/articles2")
//    public Result<List<ArticleItemVo>> getListArticlesBy2(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
//        List<ArticleEntity> articleBeans = articleNewService.selectArticles(new Page(page,pageSize));
//        List<Long> ids = articleBeans.stream().map(articleEntity -> articleEntity.getId()).collect(Collectors.toList());
//        List<UserBean> userBeans = userService.selectUsers(ids);
//        List<ArticleItemVo> articleItemVos = new ArrayList<>();
//        return ResultUtil.success(articleBeans);
//    }

    @ApiOperation(value = " 获取文章列表，带有用户信息的列表")
    @PostMapping(value = "/batchSaveArticle")
    public Result<Map> batchSaveArticles(@Valid List<ArticleEntity> articleBeans, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        if(articleNewService.saveBatch(articleBeans)){
            return ResultUtil.success(new HashMap<>());
        }
        return ResultUtil.error("批量新增文章失败");
    }

}
