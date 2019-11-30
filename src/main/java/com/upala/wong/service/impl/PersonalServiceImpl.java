package com.upala.wong.service.impl;

import com.upala.wong.mapper.PersonalMapper;
import com.upala.wong.service.PersonalService;
import com.upala.wong.utils.StringJsonUtil;
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

	/**
	 * 实名认证
	 * @param data 参数-真实名称
	 * @return 返回值
	 */
	@Override
	public Map<String, Object> reName(Map<String, Object> data) {
		Object reName = data.get("reName");
		Object id = data.get("id");
		if (reName == null || "".equals(reName)) {
			return StringJsonUtil.getResult(1002, "真实名称为空", null);
		}
		if (id == null || "".equals(id)) {
			return StringJsonUtil.getResult(1003, "没有唯一标识码", null);
		}
		Integer res = personalMapper.reName(reName.toString(), Integer.parseInt(id.toString()));
		if (res <= 0) {
			return StringJsonUtil.getResult(1004, "实名认证失败", null);
		}
		return StringJsonUtil.getResult(200, "实名认证成功", res);
	}
}
