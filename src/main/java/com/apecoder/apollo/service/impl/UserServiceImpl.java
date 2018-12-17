package com.apecoder.apollo.service.impl;

import com.apecoder.apollo.domain.UserBean;
import com.apecoder.apollo.mapper.UserMapper;
import com.apecoder.apollo.service.UserService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserBean> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public  List<UserBean>  selectListByNickName(String nickName) {
        return  userMapper.selectList(Wrappers.query(new UserBean()).eq(UserBean::getNickName,nickName)) ;
    }

    public  List<UserBean>  selectListByUserLevel(Integer user_level) {
        return  userMapper.selectList(Wrappers.query(new UserBean()).eq(UserBean::getUserLevel,user_level)) ;
    }

    public  List<UserBean>  selectUsers(List<Long> ids) {
        return  userMapper.selectBatchIds(ids);
    }

}
