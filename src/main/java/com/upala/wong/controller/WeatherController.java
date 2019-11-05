package com.upala.wong.controller;

import com.upala.wong.entity.Result;
import com.upala.wong.utils.StringJsonUtil;
import com.upala.wong.utils.WeatherUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/********************************
 *  @program imageEngine
 *  @author upala
 *  @version 0.0.1
 *  @since 2019-11-05 15:59
 *  @description
 ********************************/

@RestController
@RequestMapping("/weather")
@CrossOrigin
@Log4j2
public class WeatherController {

    /**
     * 获取天气数据
     *
     * @return 返回值
     */
    @RequestMapping(value = "getWeatherData", method = RequestMethod.POST)
    public Map<String, Object> getWeatherData(@RequestBody String ID) {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> map = StringJsonUtil.stringToJsonObject(ID);
        String id = (String) map.get("id");
        String cityStr = WeatherUtils.get(id);
        log.info("城市数据：" + cityStr);
        if (StringUtils.isEmpty(cityStr)){
            resultMap.put("message", null);
            return resultMap;
        }
        resultMap.put("msg", cityStr);
        return resultMap;
    }

}
