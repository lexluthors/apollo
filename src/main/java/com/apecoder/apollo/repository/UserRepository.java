package com.apecoder.apollo.repository;

import com.apecoder.apollo.domain.UserBean;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface UserRepository extends JpaRepository<UserBean,Integer> {

    //通过年龄来查询
//    public List<UserBean> findUserBeanBy(Integer age);

    //通过id来查询
    public UserBean findUserBeanById(Integer id);

    //通过phone来查询
    public UserBean findUserBeanByPhone(String phone);
    public List<UserBean> findUserBeansByPhone(String phone);



}
