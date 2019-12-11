package com.upala.wong.utils;

import com.alibaba.fastjson.JSONException;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

/*****************************
 *  @author 王鹏
 *  @since 2019/12/10 12:31
 *  @version 0.0.1
 *****************************/

@Log4j2
public class Message {

	/**
	 * 撒送短信
	 * @param phoneNumbers 入参
	 * @param con 入参
	 */
	public static SmsSingleSenderResult sendMessage(String[] phoneNumbers, String[] con) {
		SmsSingleSenderResult result = null;
		try {
			SmsSingleSender sender = new SmsSingleSender(FinalVarUtil.APP_ID, FinalVarUtil.APP_KEY);
			result = sender.sendWithParam("86", phoneNumbers[0], FinalVarUtil.TEMPLATE_ID, con,
					FinalVarUtil.SMS_SIGN, "", "");
			log.info("短信验证码：{}", result);
		} catch (HTTPException | JSONException | IOException e) {
			// HTTP 响应码错误/JSON 解析错误/网络 IO 错误
			log.error("错误信息：{}", e.getMessage());
		}
		return result;
	}

}
