package com.apecoder.apollo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * 拦截方法 *表示拦截所有方法，
 */
@Aspect
@Component
public class HttpAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

        @Pointcut("execution(public * com.apecoder.apollo.controller.GirlContorller.*(..))")
        public void log(){
        }

        @Before("log()")
        public void doBefore(JoinPoint JoinPoint){
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            logger.info("url={}",request.getRequestURL());
            logger.info("method={}",request.getMethod());
            logger.info("ip={}",request.getRemoteAddr());

        //类方法
        logger.info("class_method={}",JoinPoint.getSignature().getDeclaringTypeName()+"."+JoinPoint.getSignature().getName());
        //参数
        logger.info("args={}",JoinPoint.getArgs());
//        logger.info("11111111");
    }

    @After("log()")
    public void doAfter(){
        logger.info("2222222");
    }

    @AfterReturning(returning = "object",pointcut = "log()")
    public void doAfterReturning(Object object){
//        logger.info("response={}",object);
    }
}
