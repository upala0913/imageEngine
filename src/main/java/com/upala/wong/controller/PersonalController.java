package com.upala.wong.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicParameters;
import com.upala.wong.common.ResponseCommon;
import com.upala.wong.service.PersonalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@ApiResponses({
    @ApiResponse(code = 200, message = "响应成功"),
    @ApiResponse(code = 3426, message = "响应出错")
})
public class PersonalController {

    @Resource
    private PersonalService personalService;

    /**
     * 实名认证
     *
     * @param data 入参
     * @return 返回值
     */
    @RequestMapping(value = "/reName", method = RequestMethod.POST)
    @ApiOperation(value = "实名认证", notes = "实名认证")
    @ApiOperationSupport(
        author = "upala",
        params = @DynamicParameters(properties = {
            @DynamicParameter(value = "用户ID", name = "id", dataTypeClass = String.class, required = true),
            @DynamicParameter(value = "用户真实姓名", name = "reName", dataTypeClass = String.class, required = true)
        })
    )
    public ResponseCommon reName(@RequestBody Map<String, Object> data) {
        return personalService.reName(data);
    }

    /**
     * 绑定电话
     *
     * @param data 入参
     * @return 返回值
     */
    @ApiOperation(value = "绑定电话", notes = "绑定电话")
    @ApiOperationSupport(
        author = "upala",
        params = @DynamicParameters(properties = {
            @DynamicParameter(value = "用户电话", name = "mobile", dataTypeClass = String.class, required = true),
            @DynamicParameter(value = "用户短信验证码", name = "check", dataTypeClass = String.class, required = true),
            @DynamicParameter(value = "用户ID", name = "id", dataTypeClass = String.class, required = true)
        })
    )
    @RequestMapping(value = "/bindMobile", method = RequestMethod.POST)
    public ResponseCommon bindMobile(@RequestBody Map<String, Object> data) {
        return personalService.bindMobile(data);
    }

    /**
     * 获取短信验证码
     *
     * @param data 入参
     * @return 返回值
     */
    @ApiOperation(value = "获取短信验证码", notes = "获取短信验证码")
    @ApiOperationSupport(
        author = "upala",
        params = @DynamicParameters(name = "data", properties = {
            @DynamicParameter(value = "用户电话", name = "mobile", dataTypeClass = String.class, required = true),
            @DynamicParameter(value = "用户名称", name = "username", dataTypeClass = String.class, required = true),
            @DynamicParameter(value = "用户ID", name = "id", dataTypeClass = String.class, required = true)
        })
    )
    @RequestMapping(value = "/getMobileMessage", method = RequestMethod.POST)
    public ResponseCommon getMessage(@RequestBody Map<String, Object> data) {
        return personalService.getMessage(data);
    }

    /**
     * 获取邮箱验证码
     *
     * @param data 入参
     * @return 返回值
     */
    @ApiOperation(value = "获取邮箱验证码", notes = "获取短信验证码")
    @ApiOperationSupport(
        author = "upala",
        params = @DynamicParameters(name = "data", properties = {
            @DynamicParameter(value = "用户邮箱", name = "email", dataTypeClass = String.class, required = true),
            @DynamicParameter(value = "用户名称", name = "username", dataTypeClass = String.class, required = true),
            @DynamicParameter(value = "用户ID", name = "id", dataTypeClass = String.class, required = true),
        })
    )
    @RequestMapping(value = "/emailMessage", method = RequestMethod.POST)
    public ResponseCommon getEmailMessage(@RequestBody Map<String, Object> data) {
        return personalService.getEmailMessage(data);
    }

    /**
     * 绑定邮箱
     *
     * @param data 绑定邮箱的参数
     * @return 返回值--返回绑定邮箱的状态
     */
    @ApiOperation(value = "绑定邮箱", notes = "绑定邮箱")
    @ApiOperationSupport(
        author = "upala",
        params = @DynamicParameters(name = "data", properties = {
            @DynamicParameter(value = "邮箱", name = "email", dataTypeClass = String.class, required = true),
            @DynamicParameter(value = "邮箱验证码", name = "check", dataTypeClass = String.class, required = true),
            @DynamicParameter(value = "用户ID", name = "id", dataTypeClass = String.class, required = true)
        })
    )
    @RequestMapping(value = "/bindEmail", method = RequestMethod.POST)
    public ResponseCommon bindEmail(@RequestBody Map<String, Object> data) {
        return personalService.bindEmail(data);
    }

    /**
     * 获取个人信息
     *
     * @param data 入参--用户信息ID
     * @return 返回值--返回值用户信息
     */
    @ApiOperation(value = "获取个人信息", notes = "获取个人信息")
    @ApiOperationSupport(
        author = "upala",
        params = @DynamicParameters(name = "data", properties = {
            @DynamicParameter(value = "用户ID", name = "id", dataTypeClass = String.class, required = true)
        })
    )
    @RequestMapping(value = "/personal", method = RequestMethod.POST)
    public ResponseCommon getPerson(@RequestBody Map<String, Object> data) {
        return personalService.getPerson(data);
    }

    /**
     * 解除电话的绑定
     * @param param 入参
     * @return 返回值
     */
    @ApiOperation(value = "解除电话绑定", notes = "解除电话绑定")
    @ApiOperationSupport(
        author = "upala",
        params = @DynamicParameters(name = "param", properties = {
            @DynamicParameter(name = "id", value = "用户ID", dataTypeClass = String.class, required = true)
        })
    )
    @RequestMapping(value = "/removeMobile", method = RequestMethod.POST)
    public ResponseCommon removeMobile(@RequestBody Map<String, Object> param) {
        return personalService.removeMobile(param);
    }

    /**
     * 解除电话的绑定
     * @param param 入参
     * @return 返回值
     */
    @ApiOperation(value = "解除邮箱绑定", notes = "解除邮箱绑定")
    @ApiOperationSupport(
        author = "upala",
        params = @DynamicParameters(name = "param", properties = {
            @DynamicParameter(name = "id", value = "用户ID", dataTypeClass = String.class, required = true)
        })
    )
    @RequestMapping(value = "/removeEmail", method = RequestMethod.POST)
    public ResponseCommon removeEmail(@RequestBody Map<String, Object> param) {
        return personalService.removeEmail(param);
    }

}
