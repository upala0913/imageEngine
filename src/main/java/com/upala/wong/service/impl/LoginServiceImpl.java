package com.upala.wong.service.impl;

import com.upala.wong.entity.Manager;
import com.upala.wong.mapper.LoginMapper;
import com.upala.wong.service.LoginService;
import com.upala.wong.utils.StringJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.Map;

/********************************
 *  @program imageEngine
 *  @author upala
 *  @version 0.0.1
 *  @since 2019-11-07 08:43
 *  @description
 ********************************/

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    /**
     * 登陆信息
     * @param param 入参-用户信息
     * @param code 入参-服务器获取到的验证码
     * @return 返回值<b>Map<String, Object></b>
     */
    @Override
    public Map<String, Object> loginUser(Map<String, Object> param, String code) {
        String key = (String) param.get("code");
        if (!StringUtils.isEmpty(code)) {
            // 小写
            boolean b1 = key.toLowerCase().equals(code.toLowerCase());
            // 大写
            boolean b2 = key.toUpperCase().equals(code.toUpperCase());
            // 原型
            boolean b3 = key.equals(code);
            // 三个有其中一个为true，则验证码正确
            if (b1 || b2 || b3) {
                String username = ((String) param.get("username")).trim();
                String password = ((String) param.get("password")).trim();
                if (!StringUtils.isEmpty(username) || !StringUtils.isEmpty(password)) {
                    Manager manager = loginMapper.loginUser(username, password);
                    if (manager != null)
                        return StringJsonUtil.getResult(200, "登陆成功", manager);
                    else
                        return StringJsonUtil.getResult(10001, "用户名或密码错误", null);
                } else {
                    return StringJsonUtil.getResult(10002, "用户名密码不能为空", null);
                }
            }
        }
        return StringJsonUtil.getResult(10000, "验证码错误", null);
    }

}
