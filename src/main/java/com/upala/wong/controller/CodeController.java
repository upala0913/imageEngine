package com.upala.wong.controller;

import com.upala.wong.utils.StringJsonUtil;
import lombok.extern.log4j.Log4j2;
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
@RequestMapping("/code")
@CrossOrigin
@Log4j2
public class CodeController {

    /**
     * 获取天气数据
     *
     * @return 返回值
     */
    @RequestMapping(value = "getCodeInfo", method = RequestMethod.POST)
    public Map<String, Object> getWeatherData(HttpSession session) {
        String code = StringJsonUtil.getCode();
        // 将获取到的验证码保存到session中，以便登陆的时候获取
        session.setAttribute("code", code);
        return StringJsonUtil.stringToJsonObject(code);
    }

}
