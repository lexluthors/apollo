package com.apecoder.apollo.service;

import com.apecoder.apollo.domain.Result;
import com.apecoder.apollo.domain.UserBean;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface UserService extends IService<UserBean> {

    public List<UserBean> selectListByNickName(String nickName);
}
