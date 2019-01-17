package com.jiangfw.common.lang.test.json.utils;

import com.alibaba.fastjson.JSON;

/**
 * Created by jiangfw on 2019/1/8.
 */
public class FastJsonUtil {

    public static String bean2Json(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
        return JSON.parseObject(jsonStr, objClass);
    }
}
