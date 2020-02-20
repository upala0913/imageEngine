package com.upala.wong.mapper;

import com.upala.wong.entity.Manager;
import org.apache.ibatis.annotations.Param;

/*****************************
 *  @author 王鹏
 *  @since 2019/11/29 18:44
 *  @version 0.0.1
 *****************************/
public interface PersonalMapper {

	Integer reName(@Param("reName") String reName, @Param("id") String id);

	Integer bindMobile(@Param("mobile") String mobile, @Param("id") String id);

	Integer bindEmail(@Param("email") String email, @Param("id") String id);

	Manager getPersonal(@Param("id") String id);

	String getMobile(@Param("id") String id);

	String getEmail(@Param("id") String id);

	Integer removeMobile(@Param("id") String id);

	Integer removeEmail(@Param("id") String id);

}
