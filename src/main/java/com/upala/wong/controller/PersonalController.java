package com.upala.wong.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicParameters;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.upala.wong.common.ResponseCommon;
import com.upala.wong.service.PersonalService;
import com.upala.wong.utils.StringJsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/*****************************
 *  @author 王鹏
 *  @since 2019/11/29 18:12
 *  @version 0.0.1
 *****************************/

@RestController
@RequestMapping(value = "/personal")
@Log4j2
@Api(value = "个人信息API", tags = "个人信息API")
public class PersonalController {

	@Resource
	private PersonalService personalService;

	/**
	 * 实名认证
	 * @param param 入参
	 * @return 返回值
	 *
	 */
	@RequestMapping(value = "/reName", method = RequestMethod.POST)
	@ApiOperation(value = "实名认证", notes = "实名认证")
	@ApiImplicitParam(name = "param", value = "json格式传参", required = true, dataType = "json")
	public Map<String, Object> reName(@RequestBody String param) {
		Map<String, Object> data = StringJsonUtil.stringToJsonObject(param);
		return personalService.reName(data);
	}

	/**
	 * 绑定电话
	 * @param param 入参
	 * @return 返回值
	 */
	@ApiOperation(value = "绑定电话", notes = "绑定电话")
	@ApiImplicitParam(name = "param", value = "json格式传参", required = true, dataType = "json")
	@RequestMapping(value = "/bindMobile", method = RequestMethod.POST)
	public Map<String, Object> bindMobile(@RequestBody String param) {
		Map<String, Object> data = StringJsonUtil.stringToJsonObject(param);
		return personalService.bindMobile(data);
	}

	/**
	 * 获取短信验证码
	 * @param param 入参
	 * @return 返回值
	 */
	@ApiOperation(value = "获取短信验证码", notes = "获取短信验证码")
	@ApiImplicitParam(name = "param", value = "json格式传参", required = true, dataType = "json")
	@RequestMapping(value = "/getMessage", method = RequestMethod.POST)
	public Map<String, Object> getMessage(@RequestBody String param) {
		Map<String, Object> data = StringJsonUtil.stringToJsonObject(param);
		return personalService.getMessage(data);
	}

	/**
	 * 获取邮箱验证码
	 * @param param 入参
	 * @return 返回值
	 */
	@ApiOperation(value = "获取短信验证码", notes = "获取短信验证码")
	@ApiImplicitParam(name = "param", value = "json格式传参", required = true, dataType = "json")
	@RequestMapping(value = "/emailMessage", method = RequestMethod.POST)
	public Map<String, Object> getEmailMessage(@RequestBody String param) {
		Map<String, Object> data = StringJsonUtil.stringToJsonObject(param);
		return personalService.getEmailMessage(data);
	}

	/**
	 * 绑定邮箱
	 * @param param 绑定邮箱的参数
	 * @return 返回值--返回绑定邮箱的状态
	 */
	@ApiOperation(value = "绑定邮箱", notes = "绑定邮箱")
	@ApiImplicitParam(name = "param", value = "json格式传参", required = true, dataType = "json")
	@RequestMapping(value = "/bindEmail", method = RequestMethod.POST)
	public Map<String, Object> bindEmail(@RequestBody String param) {
		Map<String, Object> data = StringJsonUtil.stringToJsonObject(param);
		return personalService.bindEmail(data);
	}

	/**
	 * 获取个人信息
	 * @param param 入参--用户信息ID
	 * @return 返回值--返回值用户信息
	 */
	@ApiOperation(value = "获取个人信息", notes = "获取个人信息")
	@ApiImplicitParam(name = "param", value = "json格式传参", required = true, dataType = "json")
	@RequestMapping(value = "/personal", method = RequestMethod.POST)
	public Map<String, Object> getPerson(@RequestBody String param) {
		Map<String, Object> data = StringJsonUtil.stringToJsonObject(param);
		return personalService.getPerson(data);
	}

	@ApiOperationSupport(
			params = @DynamicParameters(name = "param", properties = {
					@DynamicParameter(value = "参数1", name = "data")
			}),
			responses = @DynamicResponseParameters(name = "result", properties = {
					@DynamicParameter(value = "响应数据", name = "res")
			}),
			author = "upala"
	)
	@ApiOperation(value = "测试接口", notes = "测试接口")
	@RequestMapping(value = "/getString", method = RequestMethod.POST)
	public Map<String, Object> getString(@RequestBody Map<String, Object> param) {
	    log.info("参数：{}", param);
        return ResponseCommon.responseSuccess("请求成功", param);
    }

}
