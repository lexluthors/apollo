package com.apecoder.apollo.repository;

import com.apecoder.apollo.domain.UserBean;
import com.fasterxml.jackson.databind.ser.Serializers;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.BaseStream;

public interface UserRepository extends BaseStream {

    //通过年龄来查询
//    public List<UserBean> findUserBeanBy(Integer age);

    //通过id来查询
    public UserBean findUserBeanById(Integer id);

    //通过phone来查询
    public UserBean findUserBeanByPhone(String phone);
    public List<UserBean> findUserBeansByPhone(String phone);



}
