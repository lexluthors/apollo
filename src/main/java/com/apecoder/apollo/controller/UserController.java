package com.apecoder.apollo.controller;

import com.apecoder.apollo.domain.Result;
import com.apecoder.apollo.domain.UserBean;
import com.apecoder.apollo.repository.UserRepository;
import com.apecoder.apollo.service.impl.UserServiceImpl;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);


    //新增一个用户
    @PostMapping(value = "/user/register")
    public Result<UserBean> userBeanAdd(@RequestParam("phone")  String phone, @RequestParam("password") String password){
        if(TextUtils.isEmpty(phone)||TextUtils.isEmpty(password)){
            return ResultUtil.error("手机号和密码不能为空");
        }
        UserBean userBean = userService.getOne(Wrappers.query(new UserBean()).eq(UserBean::getPhone,phone));
        if(null !=userBean){
            //已经注册了，请直接登录
            return ResultUtil.error("已经注册，直接登录");
        }
        userBean = new UserBean();//默认是1，普通用户
        userBean.setPhone(phone);
        userBean.setPassword(password);
        userBean.setUserLevel(1);//默认是1，普通用户
        if(userService.save(userBean)){
            return ResultUtil.success(userBean);
        }
        return ResultUtil.error("注册失败");
    }

    /**
     *
     * @param phone
     * @param password
     * @return
     */
    @PostMapping(value = "/user/login")
    public Result<UserBean> userBeanLogin(@RequestParam("phone")  String phone, @RequestParam("password") String password){
        if(TextUtils.isEmpty(phone)||TextUtils.isEmpty(password)){
            return ResultUtil.error(ResultUtil.ERROR_CODE,"手机号和密码不能为空");
        }
        UserBean userBean = userService.getOne(Wrappers.query(new UserBean()).eq(UserBean::getPhone,phone));
        if(null !=userBean){
            //已经注册了，请直接登录
            if(userBean.getPassword().equals(password)){
                //通过密码验证
                return ResultUtil.success(userBean);
            }
            return ResultUtil.error(ResultUtil.ERROR_CODE,"密码错误");
        }
        return ResultUtil.error(ResultUtil.ERROR_CODE,"未注册，请注册");
    }

    //更新一个用户
    @ApiOperation(value = "更新用户资料",notes = "修改用户个人信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "int"),
            @ApiImplicitParam(name = "age", value = "用户年龄", required = false, dataType = "int"),
            @ApiImplicitParam(name = "des", value = "个人描述，签名", required = false, dataType = "string"),
            @ApiImplicitParam(name = "nick_name", value = "昵称", required = false, dataType = "string"),
            @ApiImplicitParam(name = "name", value = "姓名", required = false, dataType = "string"),
            @ApiImplicitParam(name = "hobby", value = "爱好", required = false, dataType = "string"),
            @ApiImplicitParam(name = "gender", value = "性别", required = false, dataType = "string")
    })
    @PostMapping(value = "/user/update")
    public Result<UserBean>  userBeanUpdate(@Valid UserBean userBean, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        UserBean userBeanById = userService.getById(userBean.getId());
        if(null!=userBeanById){
            EntityCopyUtil.beanCopyWithIngore(userBean,userBeanById,"password");
            return ResultUtil.success(userService.save(userBeanById));
        }
        return ResultUtil.error("未找到该用户");
    }

    @ApiOperation(value = "根据nickname获取用户列表",notes = "获取用户列表")
    @PostMapping(value = "/get_users_nickname")
    public Result<List<UserBean>> getUsersByNickName(@RequestParam("nick_name")  String nick_name){
        if(TextUtils.isEmpty(nick_name)){
            return ResultUtil.error(ResultUtil.ERROR_CODE,"昵称不能为空");
        }
        List<UserBean> userBeans= userService.selectListByNickName(nick_name);
        return ResultUtil.success(userBeans);
    }

    @ApiOperation(value = "根据user_level获取用户列表",notes = "获取用户列表")
    @PostMapping(value = "/get_users_level")
    public Result<List<UserBean>> getUsersByUserLevel(@RequestParam("user_level")  Integer user_level){
        List<UserBean> userBeans= userService.selectListByUserLevel(user_level);
        return ResultUtil.success(userBeans);
    }
}
