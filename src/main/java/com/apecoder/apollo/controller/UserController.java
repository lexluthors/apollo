package com.apecoder.apollo.controller;

import com.apecoder.apollo.domain.Result;
import com.apecoder.apollo.domain.UserBean;
import com.apecoder.apollo.repository.UserRepository;
import com.apecoder.apollo.utils.ResultUtil;
import com.apecoder.apollo.utils.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);


    //新增一个用户
    @PostMapping(value = "/user/add")
    public Result<UserBean> userBeanAdd(@RequestParam("phone")  String phone, @RequestParam("password") String password){
        logger.error("这他妈咋回事啊？走没有user/add1111");
        if(TextUtils.isEmpty(phone)||TextUtils.isEmpty(password)){
            return ResultUtil.error(0,"手机号和密码不能为空");
        }
        int size = userRepository.findUserBeanByPhone(phone).size();
        if(size>0){
            //已经注册了，请直接登录
            return ResultUtil.error(1,"已经注册，直接登录");
        }
        logger.error("这他妈咋回事啊？user/add");
        UserBean userBean =new UserBean();
        userBean.setPhone(phone);
        userBean.setPassword(password);
        return ResultUtil.success(userRepository.save(userBean));
    }

    //更新一个用户
    @PostMapping(value = "/user/update")
    public Result<UserBean>  userBeanUpdate(@RequestParam("des")  String des, @RequestParam("id")  Integer id, @RequestParam("name") String name){
        UserBean userBean = userRepository.findUserBeanById(id);
        if(null!=userBean){
            //已经注册了，请直接登录
            userBean.setDes(des);
            userBean.setName(name);
            return ResultUtil.success(userRepository.save(userBean));
        }
        return ResultUtil.error(1,"未找到改用户");
    }
}
