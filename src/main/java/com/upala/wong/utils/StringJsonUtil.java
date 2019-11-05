package com.upala.wong.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/********************************
 *  @program imageEngine
 *  @author upala
 *  @version 0.0.1
 *  @since 2019-11-05 16:08
 *  @description
 ********************************/

public class StringJsonUtil {

    /**
     * 将字符串转换成map集合
     * @param param 入参
     * @return 返回值
     */
    public static Map<String, Object> stringToJsonObject(String param) {
        JSONObject jsonObject = JSONObject.parseObject(param);
        Map<String, Object> map = new HashMap<>(FinalVarUtil.MAP_SIZE);
        map.putAll(jsonObject);
        return map;
    }

}
