package com.apecoder.apollo.test;

import com.apecoder.apollo.config.RedisHelperImpl;
import com.apecoder.apollo.domain.UserBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 2018/12/25 14:23
 * @description TODO
 * @date Allen
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisHelperImpl redisHelper;

    @Test
    public void test() throws Exception{
//        基本写法
//        stringRedisTemplate.opsForValue().set("aaa","111");
//        Assert.assertEquals("111",stringRedisTemplate.opsForValue().get("aaa"));
//        System.out.println(stringRedisTemplate.opsForValue().get("aaa"));
        UserBean user=new UserBean();
        user.setName("lexluthor");
        user.setHobby("打篮球，羽毛球，滑板，游泳");
        redisHelper.valuePut("lexluthor",user);
        System.out.println(redisHelper.getValue("lexluthor"));
    }

    @Test
    public void testObj() throws Exception {
        UserBean user=new UserBean();
        user.setName("lexluthor");
        user.setHobby("打篮球，羽毛球，滑板，游泳");

        ValueOperations<String, UserBean> operations=redisTemplate.opsForValue();
        operations.set("502", user);
        Thread.sleep(500);
        boolean exists=redisTemplate.hasKey("502");
        if(exists){
            System.out.println(redisTemplate.opsForValue().get("502"));
        }else{
            System.out.println("exists is false");
        }
        // Assert.assertEquals("aa", operations.get("com.neo.f").getUserName());
    }
}
