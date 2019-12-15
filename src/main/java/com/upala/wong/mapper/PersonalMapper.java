package com.upala.wong.mapper;

import com.upala.wong.entity.Manager;
import org.apache.ibatis.annotations.Param;

/*****************************
 *  @author 王鹏
 *  @since 2019/11/29 18:44
 *  @version 0.0.1
 *****************************/
public interface PersonalMapper {

	Integer reName(@Param("reName") String reName, @Param("id") int id);

	Integer bindMobile(@Param("mobile") String mobile, @Param("id") int id);

	Integer bindEmail(@Param("email") String email, @Param("id") int id);

	Manager getPersonal(@Param("id") int id);

	String getMobile(@Param("id") int id);

	String getEmail(@Param("id") int id);

}
