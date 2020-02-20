package com.upala.wong.mapper;

import com.upala.wong.entity.Manager;
import org.apache.ibatis.annotations.Param;

/********************************
 *  @program imageEngine
 *  @author upala
 *  @version 0.0.1
 *  @since 2019-11-07 09:08
 *  @description
 ********************************/
public interface LoginMapper {

    Manager loginUser(@Param("param") String username, @Param("password") String password);

    Manager getPersonInfo(@Param("id") String id);

}
