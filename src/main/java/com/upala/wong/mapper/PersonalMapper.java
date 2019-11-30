package com.upala.wong.mapper;

import org.apache.ibatis.annotations.Param;

/*****************************
 *  @author 王鹏
 *  @since 2019/11/29 18:44
 *  @version 0.0.1
 *****************************/
public interface PersonalMapper {

	Integer reName(@Param("reName") String reName, @Param("id") int id);

}
