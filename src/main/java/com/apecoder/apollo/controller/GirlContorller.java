package com.apecoder.apollo.controller;

import com.apecoder.apollo.domain.Girl;
import com.apecoder.apollo.domain.Result;
import com.apecoder.apollo.repository.GirlRepository;
import com.apecoder.apollo.service.GirlService;
import com.apecoder.apollo.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

//表示该接口全部返回json格式，restful风格的注解
@RestController
public class GirlContorller {
    private final static Logger logger = LoggerFactory.getLogger(GirlContorller.class);

    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlService girlService;

    @GetMapping(value = "/girls")
    public Result<List<Girl>> girlList(){
        logger.error("这他妈咋回事啊？走没有getgirls");
        return ResultUtil.success(girlRepository.findGirlsByAgeEqualsAndCupSizeEquals(19,"F"));
    }

    //新增一个女孩
    @PostMapping(value = "/girls")
    public Girl girlAdd(@RequestParam("age")  Integer age, @RequestParam("cupSize") String cupSize){
        Girl girl =new Girl();
        girl.setAge(age);
        girl.setCupSize(cupSize);
        return girlRepository.save(girl);
    }

    //新增一个女孩，实体类写法
    @PostMapping(value = "/girls2")
    public Girl girlAdd(Girl girl){
        girl.setAge(girl.getAge());
        girl.setCupSize(girl.getCupSize());
        return girlRepository.save(girl);
    }

    //新增十万个女孩
    @PostMapping(value = "/girls/addlakh")
    public Result girlAdd(){


        for(int j=0;j<20;j++){
            List<Girl> list = new ArrayList<>();
            for(int i=0;i<500;i++){
                Girl girl = new Girl();
                girl.setAge(i+19);
                girl.setMoney(Double.valueOf(i));
                girl.setCupSize(String.valueOf(i));
                list.add(girl);
            }
            girlRepository.saveAll(list);
        }

        return ResultUtil.success();
    }


    /**
     * @Valid 表单验证
     * @param girl
     * @param bindingResult
     * @return
     */
    //新增一个女孩
    @PostMapping(value = "/girls/add")
    public Result<Girl> girlAddObject(@Valid Girl girl, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage()) ;
        }
        girl.setCupSize(girl.getCupSize());
        girl.setAge(girl.getAge());
        girl.setMoney(girl.getMoney());
        return ResultUtil.success(girlRepository.save(girl));
    }

    //查询一个女孩，通过id
    @GetMapping(value = "/girls/{id}")
    public Girl girlFindOne(@PathVariable("id") Integer id){
        return girlRepository.findGirlById(id);
    }

    //查询一个彭仁欢，通过id
    @GetMapping(value = "/danmo/{id}")
    public Girl girlPengRenHuan(@PathVariable("id") Integer id){
        return girlRepository.findGirlById(id);
    }

    //更新一個女孩
    @PutMapping(value = "/girls/{id}")
    public Girl girlUpdata(@PathVariable("id") Integer id, @RequestParam("age") Integer age, @RequestParam("cupSize") String cupSize){
        Girl girl =new Girl();
        girl.setId(id);
        girl.setAge(age);
        girl.setCupSize(cupSize);
        return girlRepository.save(girl);
    }

    //删除
    @DeleteMapping(value = "/girls/{id}")
    public void deleteGirl(@PathVariable("id") Integer id){
        girlRepository.deleteById(id);
    }

    //通过年龄查询
    @GetMapping(value = "/girls/age/{age}")
    public List<Girl> girlListByAge(@PathVariable("age") Integer age){
        return girlRepository.findByAge(age);
    }

    //查询女孩根据年龄，获取列表
    @PostMapping(value = "/girls/byage")
    public Result<List<Girl>> girlGirlsByage(@RequestParam("age")  Integer age){
        return ResultUtil.success(girlRepository.findByAge(age));
    }

    //查询女孩根据年龄，获取列表
    @PostMapping(value = "/girls/byageandcup")
    public Result<List<Girl>> girlGirlsByAgeAndCupsize(@RequestParam("age")  Integer age , @RequestParam("cupSize")  String cup_size){
        return ResultUtil.success(girlRepository.findGirlsByAgeEqualsAndCupSizeEquals(age,cup_size));
    }

    //事物提交
    @PostMapping(value = "/girls/two")
    public void girlTwo(){
        girlService.insertTwo();
    }

    @GetMapping(value = "girls/getAge/{id}")
    public void getAge(@PathVariable("id") Integer id) throws Exception{
        girlService.getAge(id);
    }
}
