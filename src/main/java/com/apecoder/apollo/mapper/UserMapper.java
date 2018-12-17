package com.apecoder.apollo.mapper;

import com.apecoder.apollo.domain.UserBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<UserBean> {

    @Select("SELECT * FROM fy_user as u LEFT JOIN fy_role r ON u.role = r.id")
    int deleteById(long id);

}
