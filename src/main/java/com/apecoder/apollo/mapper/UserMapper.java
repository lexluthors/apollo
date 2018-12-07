package com.apecoder.apollo.mapper;

import com.apecoder.apollo.domain.UserBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface UserMapper extends BaseMapper<UserBean> {

    int deleteById(long id);

}
