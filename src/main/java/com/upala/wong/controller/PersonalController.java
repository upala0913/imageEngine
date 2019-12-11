package com.upala.wong.controller;

import com.upala.wong.service.PersonalService;
import com.upala.wong.utils.StringJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

/*****************************
 *  @author 王鹏
 *  @since 2019/11/29 18:12
 *  @version 0.0.1
 *****************************/

@RestController
@RequestMapping(value = "/personal")
public class PersonalController {

	@Autowired
	private PersonalService personalService;

	/**
	 * 实名认证
	 * @param param 入参
	 * @return 返回值
	 *
	 */
	@RequestMapping(value = "/reName", method = RequestMethod.POST)
	public Map<String, Object> reName(@RequestBody String param) {
		Map<String, Object> data = StringJsonUtil.stringToJsonObject(param);
		return personalService.reName(data);
	}

	/**
	 * 绑定电话
	 * @param param 入参
	 * @return 返回值
	 */
	@RequestMapping(value = "/bindMobile", method = RequestMethod.POST)
	public Map<String, Object> bindMobile(@RequestBody String param, HttpSession session) {
		Map<String, Object> data = StringJsonUtil.stringToJsonObject(param);
		return personalService.bindMobile(data, session);
	}

	/**
	 * 获取短信验证码
	 * @param param 入参
	 * @return 返回值
	 */
	@RequestMapping(value = "/getMessage", method = RequestMethod.POST)
	public Map<String, Object> getMessage(@RequestBody String param, HttpSession session) {
		Map<String, Object> data = StringJsonUtil.stringToJsonObject(param);
		return personalService.getMessage(data, session);
	}

}
