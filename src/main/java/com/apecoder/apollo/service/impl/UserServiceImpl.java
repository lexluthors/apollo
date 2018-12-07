package com.apecoder.apollo.service.impl;

import com.apecoder.apollo.domain.UserBean;
import com.apecoder.apollo.mapper.UserMapper;
import com.apecoder.apollo.service.UserService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

public class UserServiceImpl extends ServiceImpl<UserMapper, UserBean> implements UserService {
    @Override
    public List<UserBean> selectListBySQL() {
        return null;
    }

    @Override
    public List<UserBean> selectListByWrapper(Wrapper wrapper) {
        return null;
    }

}
