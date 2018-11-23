package com.apecoder.apollo.utils;

import com.apecoder.apollo.aspect.HttpAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
    private final static Logger logger = LoggerFactory.getLogger(LogUtil.class);
    public static void loge(String msg){
        logger.error(msg);
    }
}
