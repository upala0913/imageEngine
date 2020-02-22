package com.upala.wong.mapper;

import com.upala.wong.entity.Province;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/********************************
 *  @program image
 *  @author 王鹏
 *  @since 2020/2/22 12:08
 *  @description 城市mapper
 *  @version 0.0.1
 ********************************/

public interface CityMapper {

    List<Province> queryProvinces(@Param("parentId") Integer parentId);

}
