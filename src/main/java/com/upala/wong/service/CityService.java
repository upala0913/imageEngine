package com.upala.wong.service;

import com.upala.wong.common.ResponseCommon;
import com.upala.wong.entity.Province;

import java.util.List;

/********************************
 *  @program image
 *  @author 王鹏
 *  @since 2020/2/22 12:23
 *  @description 城市信息service
 *  @version 0.0.1
 ********************************/
public interface CityService {

    public List<Province> queryProvinces(Integer parentId);

}
