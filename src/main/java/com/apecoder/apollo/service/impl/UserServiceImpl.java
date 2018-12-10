package com.apecoder.apollo.service.impl;

import com.apecoder.apollo.domain.UserBean;
import com.apecoder.apollo.mapper.UserMapper;
import com.apecoder.apollo.service.UserService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserBean> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public  List<UserBean>  selectListByNickName(String nickName) {
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("nick_name",nickName);//写表中的列名
        return  userMapper.selectList(Wrappers.query(new UserBean()).eq(UserBean::getNickName,nickName)) ;
    }

    public  List<UserBean>  selectListByUserLevel(Integer user_level) {
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("user_level",user_level);//写表中的列名
        return  userMapper.selectList(Wrappers.query(new UserBean()).eq(UserBean::getUserLevel,user_level)) ;
    }

}
