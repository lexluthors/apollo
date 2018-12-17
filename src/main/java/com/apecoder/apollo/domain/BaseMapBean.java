package com.apecoder.apollo.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author 2018/12/13 16:15
 * @description TODO
 * @date Allen
 */
@Getter
@Setter
public class BaseMapBean  implements Serializable {
    Integer page;
    Integer total;
    Integer current;
    List<ArticleItemVo> data;
}
