package com.apecoder.apollo.service;

import com.apecoder.apollo.domain.Girl;
import com.apecoder.apollo.enums.ResultEnum;
import com.apecoder.apollo.exception.GirlException;
import com.apecoder.apollo.repository.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class GirlService {

    /**
     * @Autowired  实例化对象注解
     */
    @Autowired
    private GirlRepository girlRepository;


    /**
     * @Transactional  事物提交，要么一起成功，要么都不成功
     */
    @Transactional
    public void insertTwo(){
        Girl girlA = new Girl();
        girlA.setCupSize("A");
        girlA.setAge(18);
        girlRepository.save(girlA);

        Girl girlB = new Girl();
        girlB.setCupSize("BBBB");
        girlB.setAge(19);
        girlRepository.save(girlB);
    }

    public void getAge(Integer id) throws Exception{
        Girl girl = girlRepository.findGirlById(id);
        Integer age = girl.getAge();
        if(age<10){
            //返回你还在上小学
//            throw new GirlException(100,"你还在上小学");
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        }else if(age>=10&&age<18){
            //返回你可能在上高中
            throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
        }else{
            throw new GirlException(ResultEnum.UNKNOWN_ERROR);
        }
    }
}
