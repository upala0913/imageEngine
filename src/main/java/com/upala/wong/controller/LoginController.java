package com.upala.wong.controller;

import com.upala.wong.common.FinalVarCommon;
import com.upala.wong.common.ResponseCommon;
import com.upala.wong.entity.Manager;
import com.upala.wong.service.LoginService;
import com.upala.wong.utils.StringJsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 获取验证码
     *
     * @return 返回值
     */
    @ApiOperation(value = "获取验证码", notes = "获取登录验证码")
    @RequestMapping(value = "/code/getCodeInfo", method = RequestMethod.POST)
    public Map<String, Object> getWeatherData(@ApiIgnore HttpSession session) {
        String code = StringJsonUtil.getCode(FinalVarCommon.LOGIN_CODE_INFO);
        // 将获取到的验证码保存到session中，以便登陆的时候获取
        session.setAttribute("code", code);
        return ResponseCommon.responseSuccess("获取验证码成功", code);
    }

    /**
     * 登陆信息
     * @param param 入参
     * @return 返回值
     */
    @ApiOperation(value = "判断验证码是否正确", notes = "判断验证码")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> loginUser(@ApiParam(name = "param", value = "以json格式传参,key=code", required = true)
                                         @RequestBody String param, @ApiIgnore HttpSession session) {
        Map<String, Object> map = StringJsonUtil.stringToJsonObject(param);
        String code = (String) session.getAttribute("code");
        log.info("登陆信息：{}", map);
        Map<String, Object> result = loginService.loginUser(map, code);
        Manager data = (Manager) result.get("data");
        session.setAttribute("manager", data);
        return result;
    }

    /**
     * 跳转页面
     * @return 返回值
     */
    @ApiOperation(value = "页面跳转", notes = "页面跳转")
    @RequestMapping(value = "/skip/pages", method = RequestMethod.POST)
    public Map<String, Object> skipPages(@ApiIgnore HttpSession session) {
        return getData(session);
    }

    /**
     * 获取用户信息
     * @param session 会话session
     * @return 返回值
     */
    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    @RequestMapping(value = "/adminInfo", method = RequestMethod.POST)
    public Map<String, Object> getAdminInfo(@ApiIgnore HttpSession session) {
        return getData(session);
    }

    /**
     * 退出--销毁session
     * @param session 参数
     * @return 返回值
     */
    @ApiOperation(value = "退出登录", notes = "退出登录")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Map<String, Object> logout(@ApiIgnore HttpSession session) {
        session.invalidate();
        return ResponseCommon.responseSuccess("成功推出");
    }

    /**
     * 进个人中心页面获取个人信息
     * @param param 入参
     * @return 返回值
     */
    @ApiOperation(value = "进入个人中心", notes = "进入个人中心")
    @RequestMapping(value = "/getPersonInfo", method = RequestMethod.POST)
    public Map<String, Object> toPersonal(@ApiParam(name = "param", value = "以json格式传递，key=id", required = true)
                                              @RequestBody String param) {
        log.info("参数信息：{}", param);
        Map<String, Object> map = StringJsonUtil.stringToJsonObject(param);
        return loginService.getPersonInfo(map);
    }

    private Map<String, Object> getData(@ApiIgnore HttpSession session) {
        Manager manager = (Manager) session.getAttribute("manager");
        if (manager == null)
            return ResponseCommon.responseFail("请先登录");
        else
            return ResponseCommon.responseFail("已登陆");
    }

}
