package com.upala.wong.service.impl;

import com.github.qcloudsms.SmsSingleSenderResult;
import com.upala.wong.mapper.PersonalMapper;
import com.upala.wong.service.PersonalService;
import com.upala.wong.utils.MessageUtils;
import com.upala.wong.utils.StringJsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/*****************************
 *  @author 王鹏
 *  @since 2019/11/29 18:38
 *  @version 0.0.1
 *****************************/

@Service
public class PersonalServiceImpl implements PersonalService {

	@Autowired
	private PersonalMapper personalMapper;

	private static String checkCode = StringJsonUtil.getCheck(6);

	private static String emailCode = StringJsonUtil.getCheck(6);

	/**
	 * 实名认证
	 * @param data 参数-真实名称
	 * @return 返回值
	 */
	@Override
	public Map<String, Object> reName(Map<String, Object> data) {
		String reName = (String) data.get("reName");
		String id = (String) data.get("id");
		if (StringUtils.isEmpty(reName)) {
			return StringJsonUtil.getResult(1002, "真实名称为空", null);
		}
		if (StringUtils.isEmpty(id)) {
			return StringJsonUtil.getResult(1003, "没有唯一标识码", null);
		}
		Integer res = personalMapper.reName(reName, Integer.parseInt(id));
		if (res <= 0) {
			return StringJsonUtil.getResult(1004, "实名认证失败", null);
		}
		return StringJsonUtil.getResult(200, "实名认证成功", res);
	}

	/**
	 * 绑定电话
	 * @param data 入参
	 * @return 返回值
	 */
	@Override
	public Map<String, Object> bindMobile(Map<String, Object> data) {
		String mobile = (String) data.get("mobile");
		String check = (String) data.get("check");
		String id = (String) data.get("id");
		if (StringUtils.isEmpty(mobile))
			return StringJsonUtil.getResult(1005, "电话不能为空", null);
		if (StringUtils.isEmpty(id))
			return StringJsonUtil.getResult(1003, "没有唯一的标识码", null);
		if (StringUtils.isEmpty(check))
			return StringJsonUtil.getResult(1008, "验证码为空", null);
		if (!check.equals(checkCode))
			return StringJsonUtil.getResult(1009, "验证码错误", null);
		Integer res = personalMapper.bindMobile(mobile, Integer.parseInt(id));
		if (res <= 0)
			return StringJsonUtil.getResult(1006, "绑定电话失败", null);
		return StringJsonUtil.getResult(200, "电话绑定成功", res);
	}

	/**
	 * 获取短信验证码
	 * @param data 入参
	 * @return 返回值
	 */
	@Override
	public Map<String, Object> getMessage(Map<String, Object> data) {
		String mobile = (String) data.get("mobile");
		String upala = (String) data.get("upala");
		String[] phones = {mobile};
		String[] cons = {upala, checkCode};
		SmsSingleSenderResult result = MessageUtils.sendMessage(phones, cons);
		if (result == null) {
			return StringJsonUtil.getResult(1007, "短信验证码发送失败", null);
		}
		return StringJsonUtil.getResult(200, "短信验证码已发送至"+mobile+"，请注意查收", result);
	}

	@Override
	public Map<String, Object> getEmailMessage(Map<String, Object> data) {
		String email = (String) data.get("email");
		String str = data.get("upala") + "先生/女士，你申请账号的验证码是：" + emailCode;
		boolean flag = MessageUtils.sendMail(email, str);
		return null;
	}
}
