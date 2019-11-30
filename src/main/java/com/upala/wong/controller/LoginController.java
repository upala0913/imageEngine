package com.upala.wong.controller;

import com.upala.wong.entity.Manager;
import com.upala.wong.service.LoginService;
import com.upala.wong.utils.StringJsonUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 获取天气数据
     *
     * @return 返回值
     */
    @RequestMapping(value = "/code/getCodeInfo", method = RequestMethod.POST)
    public Map<String, Object> getWeatherData(HttpSession session) {
        String code = StringJsonUtil.getCode();
        // 将获取到的验证码保存到session中，以便登陆的时候获取
        session.setAttribute("code", code);
        return StringJsonUtil.stringToJsonObject(code);
    }

    /**
     * 登陆信息
     * @param param 入参
     * @return 返回值
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> loginUser(@RequestBody String param, HttpSession session) {
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
    @RequestMapping(value = "/skip/pages", method = RequestMethod.POST)
    public Map<String, Object> skipPages(HttpSession session) {
        return getData(session);
    }

    /**
     * 获取用户信息
     * @param session 会话session
     * @return 返回值
     */
    @RequestMapping(value = "/adminInfo", method = RequestMethod.POST)
    public Map<String, Object> getAdminInfo(HttpSession session) {
        return getData(session);
    }

    /**
     * 退出--销毁session
     * @param session 参数
     * @return 返回值
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Map<String, Object> logout(HttpSession session) {
        session.invalidate();
        return StringJsonUtil.getResult(200, "销毁成功", null);
    }

    /**
     * 进个人中心页面获取个人信息
     * @param param 入参
     * @return 返回值
     */
    @RequestMapping(value = "/getPersonInfo", method = RequestMethod.POST)
    public Map<String, Object> toPersonal(@RequestBody String param) {
        log.info("参数信息：{}", param);
        Map<String, Object> map = StringJsonUtil.stringToJsonObject(param);
        return loginService.getPersonInfo(map);
    }

    private Map<String, Object> getData(HttpSession session) {
        Manager manager = (Manager) session.getAttribute("manager");
        if (manager == null)
            return StringJsonUtil.getResult(10003, "请先登录", null);
        else
            return StringJsonUtil.getResult(10004, "已登陆", manager);
    }

}
