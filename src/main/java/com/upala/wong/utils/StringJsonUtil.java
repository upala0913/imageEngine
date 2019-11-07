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
        Map<String, Object> map = new HashMap<>(FinalVarUtil.MAP_SIZE);
        JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.parseObject(param);
            map.putAll(jsonObject);
        } catch (RuntimeException e) {
            map.put("message", param);
        }
        return map;
    }

    /**
     * 获取验证码信息
     * @return 返回值
     */
    public static String getCode() {
        String str = "qwertyuioplkjhgfdsazxcvbnmQAZXSWEDCVFRTGBNHYUJMKLOIP1234567890@#$%&";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            int ran = (int)(Math.random()*67);
            sb.append(str.charAt(ran));
        }
        return sb.toString();
    }

    /**
     * 获取返回数据对象
     * @param status 代码号
     * @param message 信息
     * @param data 数据
     * @return 返回值<b>map<String, Object></b>
     */
    public static Map<String, Object> getResult(int status, String message, Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        map.put("message", message);
        map.put("data", data);
        return map;
    }

}
