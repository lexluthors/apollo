package com.apecoder.apollo.utils;


import com.apecoder.apollo.domain.EmptyData;
import com.apecoder.apollo.domain.Result;
import com.apecoder.apollo.domain.ResultData;

import java.util.ArrayList;

public class ResultUtil {
    public static int ERROR_CODE = 0;
    public static int SUCCESS_CODE = 1;

    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(1);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result success(Integer code, String msg) {
        return success(code, msg, null);
    }

    public static Result success(Integer code, String msg, Object object) {
        Result result = new Result();
        result.setCode(1);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(new EmptyData());
        return result;
    }
    public static Result error(String msg) {
        return error(ERROR_CODE,msg);
    }
}
