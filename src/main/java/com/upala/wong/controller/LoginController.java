package com.upala.wong.controller;

import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicParameters;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.upala.wong.common.FinalVarCommon;
import com.upala.wong.common.ResponseCommon;
import com.upala.wong.entity.Manager;
import com.upala.wong.service.LoginService;
import com.upala.wong.utils.StringJsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/********************************
 *  @program imageEngine
 *  @author upala
 *  @version 0.0.1
 *  @since 2019-11-05 15:59
 *  @description
 ********************************/

@RestController
@RequestMapping("/user")
@CrossOrigin
@Log4j2
@Api(value = "登录相关的API", tags = "登录相关的API")
@ApiResponses({
    @ApiResponse(code = 200, message = "响应成功"),
    @ApiResponse(code = 3426, message = "响应出错")
})
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 获取验证码
     *
     * @return 返回值
     */
    @ApiOperation(value = "获取验证码", notes = "获取登录验证码")
    @ApiOperationSupport(
        author = "upala"
    )
    @RequestMapping(value = "/code/getCodeInfo", method = RequestMethod.POST)
    public ResponseCommon getWeatherData(@ApiIgnore HttpSession session) {
        String code = StringJsonUtil.getCode(FinalVarCommon.LOGIN_CODE_INFO);
        // 将获取到的验证码保存到session中，以便登陆的时候获取
        session.setAttribute("code", code);
        return ResponseCommon.responseSuccess("获取验证码成功", code);
    }

    /**
     * 登陆信息
     *
     * @param param 入参
     * @return 返回值
     */
    @ApiOperation(value = "登录", notes = "登录")
    @ApiOperationSupport(
        author = "upala",
        params = @DynamicParameters(name = "param", properties = {
            @DynamicParameter(value = "验证码", name = "code", dataTypeClass = String.class, required = true),
            @DynamicParameter(value = "用户名", name = "username", dataTypeClass = String.class, required = true),
            @DynamicParameter(value = "密码", name = "password", dataTypeClass = String.class, required = true)
        })
    )
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseCommon loginUser(@RequestBody Map<String, Object> param, @ApiIgnore HttpSession session) {
        String code = (String) session.getAttribute("code");
        log.info("登陆信息：{}", param);
        ResponseCommon result = loginService.loginUser(param, code);
        Manager data = (Manager) result.getData();
        session.setAttribute("manager", data);
        return result;
    }

    /**
     * 跳转页面
     *
     * @return 返回值
     */
    @ApiOperation(value = "页面跳转", notes = "页面跳转")
    @ApiOperationSupport(
        author = "upala"
    )
    @RequestMapping(value = "/skip/pages", method = RequestMethod.POST)
    public ResponseCommon skipPages(@ApiIgnore HttpSession session) {
        return getData(session);
    }

    /**
     * 获取用户信息
     *
     * @param session 会话session
     * @return 返回值
     */
    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    @ApiOperationSupport(
        author = "upala"
    )
    @RequestMapping(value = "/adminInfo", method = RequestMethod.POST)
    public ResponseCommon getAdminInfo(@ApiIgnore HttpSession session) {
        return getData(session);
    }

    /**
     * 退出--销毁session
     *
     * @param session 参数
     * @return 返回值
     */
    @ApiOperation(value = "退出登录", notes = "退出登录")
    @ApiOperationSupport(
        author = "upala"
    )
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseCommon logout(@ApiIgnore HttpSession session) {
        session.invalidate();
        return ResponseCommon.responseSuccess("成功推出");
    }

    /**
     * 进个人中心页面获取个人信息
     *
     * @param data 入参
     * @return 返回值
     */
    @ApiOperation(value = "进入个人中心", notes = "进入个人中心")
    @ApiOperationSupport(
        author = "upala",
        params = @DynamicParameters(name = "Object", properties = {
            @DynamicParameter(value = "用户ID", name = "id", dataTypeClass = String.class, required = true)
        })
    )
    @RequestMapping(value = "/getPersonInfo", method = RequestMethod.POST)
    public ResponseCommon toPersonal(@RequestBody Map<String, Object> data) {
        log.info("参数信息：{}", data);
        return loginService.getPersonInfo(data);
    }

    private ResponseCommon getData(@ApiIgnore HttpSession session) {
        Manager manager = (Manager) session.getAttribute("manager");
        if (manager == null)
            return ResponseCommon.responseFail("请先登录");
        else
            return ResponseCommon.responseSuccess("已登陆");
    }

}
