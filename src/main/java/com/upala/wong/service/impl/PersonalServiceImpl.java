package com.upala.wong.service.impl;

import com.github.qcloudsms.SmsSingleSenderResult;
import com.upala.wong.mapper.PersonalMapper;
import com.upala.wong.service.PersonalService;
import com.upala.wong.utils.EmailUtils;
import com.upala.wong.utils.ExceptionUtils;
import com.upala.wong.utils.MessageUtils;
import com.upala.wong.utils.StringJsonUtil;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
public class PersonalServiceImpl implements PersonalService {

	@Autowired
	private PersonalMapper personalMapper;

	@Autowired
	private EmailUtils emailUtils;

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
			return MessageUtils.getResult(1002, "真实名称为空", null);
		}
		if (StringUtils.isEmpty(id)) {
			return MessageUtils.getResult(1003, "没有唯一标识码", null);
		}
		Integer res = personalMapper.reName(reName, Integer.parseInt(id));
		if (res <= 0) {
			return MessageUtils.getResult(1004, "实名认证失败", null);
		}
		return MessageUtils.getResult(200, "实名认证成功", res);
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
			return MessageUtils.getResult(1005, "电话不能为空", null);
		if (StringUtils.isEmpty(id))
			return MessageUtils.getResult(1003, "没有唯一的标识码", null);
		if (StringUtils.isEmpty(check))
			return MessageUtils.getResult(1008, "验证码为空", null);
		if (!check.equals(checkCode))
			return MessageUtils.getResult(1009, "验证码错误", null);
		Integer res = personalMapper.bindMobile(mobile, Integer.parseInt(id));
		if (res <= 0)
			return MessageUtils.getResult(1006, "绑定电话失败", null);
		return MessageUtils.getResult(200, "电话绑定成功", res);
	}

	/**
	 * 获取短信验证码
	 * @param data 入参
	 * @return 返回值
	 */
	@Override
	public Map<String, Object> getMessage(Map<String, Object> data) {
		String mobile = (String) data.get("mobile");
		String name = (String) data.get("username");
		String[] phones = {mobile};
		String[] cons = {name, checkCode};
		SmsSingleSenderResult result = MessageUtils.sendMessage(phones, cons);
		if (result == null) {
			return MessageUtils.getResult(1007, "短信验证码发送失败", null);
		}
		return MessageUtils.getResult(200, "短信验证码已发送至"+mobile+"，请注意查收", checkCode);
	}

	/**
	 * 获取邮箱验证码
	 * @param data 入参
	 * @return 返回值
	 */
	@Override
	public Map<String, Object> getEmailMessage(Map<String, Object> data) {
		String email = (String) data.get("email");
		String name = (String) data.get("username");
		if (StringUtils.isEmpty(email))
			return MessageUtils.getResult(1010, "邮箱为空", null);
		if (StringUtils.isEmpty(name))
			return MessageUtils.getResult(1010, "邮箱为空", null);
		String str = name + "先生/女士，你申请账号的验证码是：" + emailCode;
		try {
			emailUtils.sendSimpleMail(email, "验证码", str);
			return MessageUtils.getResult(200, "验证码已发送至" + email + "邮箱", emailCode);
		} catch (ExceptionUtils e) {
			log.error("发送邮件错误：{}", e.getMessage());
			return MessageUtils.getResult(1009, "邮箱验证码发送失败", null);
		}
	}

	/**
	 * 绑定邮箱
	 * @param data 参数
	 * @return 返回值
	 */
	@Override
	public Map<String, Object> bindEmail(Map<String, Object> data) {
		String email = (String) data.get("email");
		String id = (String) data.get("id");
		String check = (String) data.get("check");
		if (StringUtils.isEmpty(check))
			return MessageUtils.getResult(1014, "邮箱验证码为空，操作终止", null);
		if (!emailCode.equals(check))
			return MessageUtils.getResult(1011, "邮箱验证码不正确", null);
		if (StringUtils.isEmpty(email))
			return MessageUtils.getResult(1012, "邮箱为空，请重新输入", null);
		if (StringUtils.isEmpty(id))
			return MessageUtils.getResult(1013, "账户序列号为空，操作终止", null);
		Integer res = personalMapper.bindEmail(email, Integer.parseInt(id));
		if (res <= 0)
			return MessageUtils.getResult(1015, "绑定邮箱失败", null);
		return MessageUtils.getResult(200, "绑定邮箱成功", res);
	}

}
