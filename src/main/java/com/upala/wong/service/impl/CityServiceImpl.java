package com.upala.wong.service.impl;

import com.upala.wong.entity.Province;
import com.upala.wong.mapper.CityMapper;
import com.upala.wong.service.CityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/********************************
 *  @program image
 *  @author 王鹏
 *  @since 2020/2/22 12:25
 *  @description 城市信息实现类
 *  @version 0.0.1
 ********************************/

@Service
public class CityServiceImpl implements CityService {

    @Resource
    private CityMapper cityMapper;

    /**
     * 查询城市信息
     * @param parentId 入参
     * @return 返回值
     */
    @Override
    public List<Province> queryProvinces(Integer parentId) {
        List<Province> provinces = cityMapper.queryProvinces(parentId);
        if (provinces == null || provinces.size() == 0)
            return Collections.emptyList();
        return provinces;
    }
}
