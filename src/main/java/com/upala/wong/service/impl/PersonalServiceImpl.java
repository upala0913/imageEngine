package com.upala.wong.service.impl;

import com.github.qcloudsms.SmsSingleSenderResult;
import com.upala.wong.common.FinalVarCommon;
import com.upala.wong.common.ResponseCommon;
import com.upala.wong.entity.Manager;
import com.upala.wong.mapper.PersonalMapper;
import com.upala.wong.service.PersonalService;
import com.upala.wong.utils.EmailUtils;
import com.upala.wong.utils.ExceptionUtils;
import com.upala.wong.utils.MessageUtils;
import com.upala.wong.utils.StringJsonUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/*****************************
 *  @author 王鹏
 *  @since 2019/11/29 18:38
 *  @version 0.0.1
 *****************************/

@Service
@Log4j2
public class PersonalServiceImpl implements PersonalService {

	@Resource
	private PersonalMapper personalMapper;

	@Resource
	private EmailUtils emailUtils;

	// 短信验证码
	private static String checkCode = StringJsonUtil.getCheck(FinalVarCommon.MESSAGE_CODE);

	// 邮箱验证码
	private static String emailCode = StringJsonUtil.getCheck(FinalVarCommon.MESSAGE_CODE);

	/**
	 * 实名认证
	 * @param data 参数-真实名称
	 * @return 返回值
	 */
	@Override
	public ResponseCommon reName(Map<String, Object> data) {
		String reName = (String) data.get("reName");
		String id = data.get("id").toString();
		if (StringUtils.isEmpty(reName)) {
			return ResponseCommon.responseFail("真实名称为空");
		}
		if (StringUtils.isEmpty(id)) {
			return ResponseCommon.responseFail("没有唯一标识码");
		}
		Integer res = personalMapper.reName(reName, id);
		if (res <= 0) {
			return ResponseCommon.responseFail("实名认证失败");
		}
		return ResponseCommon.responseSuccess("实名认证成功", res);
	}

	/**
	 * 绑定电话
	 * @param data 入参
	 * @return 返回值
	 */
	@Override
	public ResponseCommon bindMobile(Map<String, Object> data) {
		String mobile = (String) data.get("mobile");
		String check = (String) data.get("check");
		String id = (String) data.get("id");
		if (StringUtils.isEmpty(mobile))
			return ResponseCommon.responseFail("电话不能为空");
		if (StringUtils.isEmpty(check))
			return ResponseCommon.responseFail("验证码为空");
		if (!check.equals(checkCode))
			return ResponseCommon.responseFail("验证码错误");
		Integer res = personalMapper.bindMobile(mobile, id);
		if (res <= 0)
			return ResponseCommon.responseFail("绑定电话失败");
		return ResponseCommon.responseSuccess("电话绑定成功", res);
	}

	/**
	 * 获取短信验证码
	 * @param data 入参
	 * @return 返回值
	 */
	@Override
	public ResponseCommon getMessage(Map<String, Object> data) {
		String mobile = (String) data.get("mobile");
		String name = (String) data.get("username");
		String id = (String) data.get("id");
		if (StringUtils.isEmpty(id))
			return ResponseCommon.responseFail("没有唯一的标识码", null);
		String mobile1 = personalMapper.getMobile(id);
		if (!StringUtils.isEmpty(mobile1)) // 电话已绑定，则不需要再次绑定
			return ResponseCommon.responseFail("电话已绑定", mobile1);
		String[] phones = {mobile};
		String[] cons = {name, checkCode};
		SmsSingleSenderResult result = MessageUtils.sendMessage(phones, cons);
		if (result == null) {
			return ResponseCommon.responseFail("短信验证码发送失败");
		}
		return ResponseCommon.responseSuccess("短信验证码已发送至"+mobile+"，请注意查收", checkCode);
	}

	/**
	 * 获取邮箱验证码
	 * @param data 入参
	 * @return 返回值
	 */
	@Override
	public ResponseCommon getEmailMessage(Map<String, Object> data) {
		String email = (String) data.get("email");
		String name = (String) data.get("username");
		String id = (String) data.get("id");
		if (StringUtils.isEmpty(id))
			return ResponseCommon.responseFail("账户序列号为空，操作终止");
		String email1 = personalMapper.getEmail(id);
		// 邮箱已绑定，则无需再次绑定
		if (!StringUtils.isEmpty(email1))
			return ResponseCommon.responseFail("邮箱已绑定", email1);
		if (StringUtils.isEmpty(email))
			return ResponseCommon.responseFail("邮箱为空");
		if (StringUtils.isEmpty(name))
			return ResponseCommon.responseFail("姓名为空");
		String str = name + "先生/女士，你申请账号的验证码是：" + emailCode;
		try {
			emailUtils.sendSimpleMail(email, "验证码", str);
			return ResponseCommon.responseSuccess("验证码已发送至" + email + "邮箱", emailCode);
		} catch (ExceptionUtils e) {
			log.error("发送邮件错误：{}", e.getMessage());
			return ResponseCommon.responseFail("邮箱验证码发送失败");
		}
	}

	/**
	 * 绑定邮箱
	 * @param data 参数
	 * @return 返回值
	 */
	@Override
	public ResponseCommon bindEmail(Map<String, Object> data) {
		String email = (String) data.get("email");
		String check = (String) data.get("check");
		String id = (String) data.get("id");
		if (StringUtils.isEmpty(id))
			return ResponseCommon.responseFail("账户序列号为空，操作终止");
		if (StringUtils.isEmpty(check))
			return ResponseCommon.responseFail("邮箱验证码为空，操作终止");
		if (!emailCode.equals(check))
			return ResponseCommon.responseFail("邮箱验证码不正确");
		if (StringUtils.isEmpty(email))
			return ResponseCommon.responseFail("邮箱为空，请重新输入");
		Integer res = personalMapper.bindEmail(email, id);
		if (res <= 0)
			return ResponseCommon.responseFail("绑定邮箱失败");
		return ResponseCommon.responseSuccess("绑定邮箱成功", res);
	}

	/**
	 * 获取个人信息
	 * @param data 入参
	 * @return 返回值
	 */
	@Override
	public ResponseCommon getPerson(Map<String, Object> data) {
		String id = (String) data.get("id");
		if (StringUtils.isEmpty(id))
			return ResponseCommon.responseFail("账户序列号为空，操作终止");
		Manager personal = personalMapper.getPersonal(id);
		if (personal == null)
			return ResponseCommon.responseFail("没有该用户");
		return ResponseCommon.responseSuccess("查询成功", personal);
	}

	/**
	 * 解除电话绑定
	 * @param data 入参
	 * @return 返回值
	 */
	@Override
	public ResponseCommon removeMobile(Map<String, Object> data) {
		return removeBind(data, "电话", "mobile");
	}

	/**
	 * 解除邮箱绑定
	 * @param data 入参
	 * @return 返回值
	 */
	@Override
	public ResponseCommon removeEmail(Map<String, Object> data) {
		return removeBind(data, "邮箱", "email");
	}

	private ResponseCommon removeBind(Map<String, Object> data, String message, String flag) {
		Integer res = null;
		Object idObj = data.get("id");
		if (idObj == null)
			return ResponseCommon.responseFail("用户ID为空");
		switch(flag) {
			case "mobile":
				res = personalMapper.removeMobile(idObj.toString());
				break;
			case "email":
				res = personalMapper.removeEmail(idObj.toString());
				break;
		}
		if (res == null)
			return ResponseCommon.responseFail(message + "解绑失败");
		return ResponseCommon.responseSuccess(message + "解绑成功", res);
	}

}
