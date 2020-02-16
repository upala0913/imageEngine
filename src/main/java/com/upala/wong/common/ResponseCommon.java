package com.upala.wong.common;

import java.util.HashMap;
import java.util.Map;

/********************************
 *  @program image
 *  @author 王鹏
 *  @since 2020/2/15 14:33
 *  @description 返回公共类
 *  @version 0.0.1
 ********************************/

public class ResponseCommon {

    /**
     * 成功返回，带数据
     * @param obj 数据
     * @return 返回map集合
     */
    public static Map<String, Object> responseSuccess(String msg, Object obj) {
        Map<String, Object> map = new HashMap<>(FinalVarCommon.MAP_SIZE);
        map.put("status", FinalVarCommon.RESULT_SUCCESS_FLAG);
        map.put("code", FinalVarCommon.RESULT_SUCCESS_CODE);
        map.put("message", msg);
        map.put("data", obj);
        return map;
    }

    /**
     * 成功返回，不带数据
     * @return 返回map集合
     */
    public static Map<String, Object> responseSuccess(String message) {
        Map<String, Object> map = new HashMap<>(FinalVarCommon.MAP_SIZE);
        map.put("status", FinalVarCommon.RESULT_SUCCESS_FLAG);
        map.put("code", FinalVarCommon.RESULT_SUCCESS_CODE);
        map.put("message", message);
        return map;
    }

    /**
     * 错误返回
     * @param message 错误信息
     * @return 返回map集合
     */
    public static Map<String, Object> responseFail(String message) {
        Map<String, Object> map = new HashMap<>(FinalVarCommon.MAP_SIZE);
        map.put("status", FinalVarCommon.RESULT_FAIL_FLAG);
        map.put("code", FinalVarCommon.RESULT_FAIL_CODE);
        map.put("message", message);
        return map;
    }

    /**
     * 错误返回
     * @param message 错误信息
     * @return 返回map集合
     */
    public static Map<String, Object> responseFail(String message, Object data) {
        Map<String, Object> map = new HashMap<>(FinalVarCommon.MAP_SIZE);
        map.put("status", FinalVarCommon.RESULT_FAIL_FLAG);
        map.put("code", FinalVarCommon.RESULT_FAIL_CODE);
        map.put("message", message);
        map.put("data", data);
        return map;
    }

}
