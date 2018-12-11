package com.apecoder.apollo.repository;

import com.apecoder.apollo.domain.Girl;

import java.util.List;
import java.util.stream.BaseStream;

public interface GirlRepository extends BaseStream  {

    //通过年龄来查询
    public List<Girl> findByAge(Integer age);
    //通过id查询一个对象
    public Girl findGirlById(Integer id);

    public List<Girl> findGirlsByAgeEqualsAndCupSizeEquals(Integer age, String cupSize);
}
