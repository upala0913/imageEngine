package com.upala.wong.utils;

import com.alibaba.fastjson.JSONObject;
import com.upala.wong.common.FinalVarCommon;

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
	 *
	 * @param param 入参
	 * @return 返回值
	 */
	public static Map<String, Object> stringToJsonObject(String param) {
		Map<String, Object> map = new HashMap<>(FinalVarCommon.MAP_SIZE);
		try {
			JSONObject jsonObject = JSONObject.parseObject(param);
			map.putAll(jsonObject);
		} catch (RuntimeException e) {
			map.put("message", param);
		}
		return map;
	}

	/**
	 * 获取验证码信息
	 *
	 * @return 返回值
	 */
	public static String getCode(int len) {
		String str = "qwertyuioplkjhgfdsazxcvbnmQAZXSWEDCVFRTGBNHYUJMKLOIP1234567890";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			int ran = (int) (Math.random() * str.length());
			sb.append(str.charAt(ran));
		}
		return sb.toString();
	}

	/**
	 * 获取校验码
	 * @param len 入参
	 * @return 返回值
	 */
	public static String getCheck(int len) {
		StringBuffer sb = new StringBuffer();
		int size = 0;
		do {
			int rand = (int) (Math.random()*10 + 1);
			size = sb.toString().length() + 1;
			sb.append(rand);
		} while (size < len);
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(getCheck(6));
	}

}
