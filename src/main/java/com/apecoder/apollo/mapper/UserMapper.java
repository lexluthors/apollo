package com.apecoder.apollo.mapper;

import com.apecoder.apollo.domain.UserBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserBean> {

    int deleteById(long id);

}
