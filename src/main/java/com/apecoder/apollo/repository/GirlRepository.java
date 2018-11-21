package com.apecoder.apollo.repository;

import com.apecoder.apollo.domain.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GirlRepository extends JpaRepository<Girl,Integer> {

    //通过年龄来查询
    public List<Girl> findByAge(Integer age);
    //通过id查询一个对象
    public Girl findGirlById(Integer id);

    public List<Girl> findGirlsByAgeEqualsAndCupSizeEquals(Integer age, String cupSize);
}
