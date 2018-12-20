package com.apecoder.apollo.controller;

import com.alibaba.fastjson.JSON;
import com.apecoder.apollo.domain.*;
import com.apecoder.apollo.service.impl.ArticleNewServiceImpl;
import com.apecoder.apollo.service.impl.CollectServiceImpl;
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
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Api(value = "ArticleController", description = "文章管理相关接口")
@RestController
public class ArticleController {

    @Autowired
    private ArticleNewServiceImpl articleNewService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private CollectServiceImpl collectService;

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
        ArticleEntity articleBean1 = articleNewService.getOneByLink(articleBean.getLink());
        if (null != articleBean1) {
            return ResultUtil.error("该文章已经存在了");
        }
        //审核中0
        articleBean.setAuditSatus(0);
        if (articleNewService.save(articleBean)) {
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
            @ApiImplicitParam(name = "id", value = "文章id", required = true, dataType = "Long"),
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
                if (articleNewService.updateById(articleBean1)) {
                    return ResultUtil.success(articleBean1);
                }
            }
            return ResultUtil.error("你无权修改此文章");
        }
        return ResultUtil.error("未找到该文章");
    }


    //更新文章的点赞数，收藏数，阅读量，评论数
    @ApiOperation(value = "更新文章属性点赞数，收藏数，阅读量，评论数", notes = "更新文章属性接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId", value = "文章id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "type", value = "0,1,2,3分别代表 收藏，点赞，阅读，评论", required = true, dataType = "int"),
            @ApiImplicitParam(name = "typeNum", value = "传1代表加1次，-1代表 取消，减1次。", required = true, dataType = "int")
    })
    @PostMapping(value = "/updateArticleNumber")
    public Result updateArticleNumber(@RequestParam(value = "articleId") Long articleId,@RequestParam(value = "userId") Long userId, @RequestParam(value = "type") Integer type, @RequestParam(value = "typeNum") Integer typeNum) {
        ArticleEntity articleBean1 = articleNewService.getById(articleId);
        CollectEntity collectEntity = collectService.getOne(Wrappers.query(new CollectEntity()).eq(CollectEntity::getUserId,userId).eq(CollectEntity::getArticleId,articleId));
        CollectEntity collectNewEntity = new CollectEntity();
        collectNewEntity.setArticleId(articleId);
        collectNewEntity.setUserId(userId);
        if (null != articleBean1) {
            switch (type) {
                case 0:
                    //收藏
                    if (typeNum == 1) {
                        //加一次收藏
                        if(collectEntity!=null){
                            if(collectEntity.getCollectStatus()==1){
                                //已经收藏了
                                return ResultUtil.error("该文章已经收藏过了");
                            }
                            //未收藏，添加到收藏列表中
                            collectEntity.setCollectStatus(1);
                            collectEntity.updateById();
                        }else{
                            //未收藏过，第一次加入收藏列表
                            collectNewEntity.setCollectStatus(1);
                            collectService.save(collectNewEntity);
                        }
                        articleBean1.setCollect(articleBean1.getCollect() + 1);
                    }else{
                        //取消收藏
                        if(collectEntity!=null){
                            //说明已经收藏了，收藏字段置位0即可
                            if(collectEntity.getCollectStatus()==1){
                                collectEntity.setCollectStatus(0);
                                collectEntity.updateById();
                                break;
                            }
                        }
                        return ResultUtil.error("你还没有收藏，请先收藏再取消");
                    }
                    break;
                case 1:
                    //点赞
                    if (typeNum == 1) {
                        //加一次点赞
                        if(collectEntity!=null){
                            if(collectEntity.getPraiseStatus()==1){
                                //已经收藏了
                                return ResultUtil.error("该文章已经收藏过了");
                            }
                            //未收藏，添加到收藏列表中
                            collectEntity.setPraiseStatus(1);
                            collectEntity.updateById();
                        }else{
                            //未点赞过，第一次点赞
                            collectNewEntity.setPraiseStatus(1);
                            collectService.save(collectNewEntity);
                        }
                        articleBean1.setPraise(articleBean1.getPraise() + 1);
                    }else{
                        //取消点赞
                        if(collectEntity!=null){
                            //说明已经点赞了，点赞字段置位0即可
                            if(collectEntity.getPraiseStatus()==1){
                                collectEntity.setCollectStatus(0);
                                collectEntity.updateById();
                                break;
                            }
                        }
                        return ResultUtil.error("你还没有收藏，请先收藏再取消");
                    }
                    break;
                case 2:
                    //阅读，
                    if (typeNum == 1) {
                        //加一次阅读
                        articleBean1.setPageViews(articleBean1.getPageViews() + 1);
                    }
                    break;
                case 3:
                    //评论
                    if (typeNum == 1) {
                        //加一次评论
                        articleBean1.setComments(articleBean1.getComments() + 1);
                    }
                    break;
                default:
                    break;
            }
            if (articleNewService.updateById(articleBean1)) {
                return ResultUtil.success(articleBean1);
            }
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
        Page<ArticleEntity> articleEntityIPage = new Page<>(page, pageSize);
        List<ArticleEntity> articleBeans = articleNewService.selectListByCategory(category, articleEntityIPage);
        List<Long> ids = articleBeans.stream().map(ArticleEntity::getContributorId).collect(Collectors.toList());
        List<UserBean> users = (List<UserBean>) userService.listByIds(ids);

        Map<Long, UserBean> userBeanMap = users.stream().collect(Collectors.toMap(UserBean::getId, Function.identity()));
        for (ArticleEntity articleBean : articleBeans) {
            articleBean.setUser(userBeanMap.get(articleBean.getContributorId()));
        }
        return ResultUtil.success(articleBeans);

    }

    @ApiOperation(value = " 获取文章列表，带有用户信息的列表，按更新时间倒叙排序")
    @PostMapping(value = "/articles")
    public Result<List<ArticleItemVo>> getListArticles(@RequestParam("category") Integer category,@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        List<ArticleItemVo> articleBeans = articleNewService.getListArticles(category,new Page(page, pageSize));
        return ResultUtil.success(articleBeans);
    }

//    @ApiOperation(value = " 获取文章列表，带有用户信息的列表")
//    @PostMapping(value = "/articles2")
//    public Result<List<ArticleItemVo>> getListArticlesBy2(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
//        List<ArticleEntity> articleBeans = artileNewService.selectArticles(new Page(page,pageSize));
//        List<Long> ids = articleBeans.stream().map(articleEntity -> articleEntity.getId()).collect(Collectors.toList());
//        List<UserBean> userBeans = userService.selectUsers(ids);
//        List<ArticleItemVo> articleItemVos = new ArrayList<>();
//        return ResultUtil.success(articleBeans);
//    }

    @ApiOperation(value = " 获取文章列表，带有用户信息的列表")
    @PostMapping(value = "/batchSaveArticle")
    public Result<Map> batchSaveArticles(@RequestParam(value = "jsonArray") String jsonArray) {
        List<ArticleEntity> articleEntityList = JSON.parseArray(jsonArray,ArticleEntity.class);
        if (articleNewService.saveBatch(articleEntityList)) {
            return ResultUtil.success("批量新增文章成功");
        }
        return ResultUtil.error("批量新增文章失败");
    }

    @ApiOperation(value = "获取所有文章列表，根据时间倒叙排序")
    @PostMapping(value = "/getAllArticleList")
    public Result<List<ArticleEntity>> getAllArticleList(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        return ResultUtil.success(articleNewService.getAllArticleList(page, pageSize));
    }
}
