package com.upala.wong.service;

import com.upala.wong.common.ResponseCommon;

import java.util.Map;

/********************************
 *  @program imageEngine
 *  @author upala
 *  @version 0.0.1
 *  @since 2019-11-07 08:41
 *  @description
 ********************************/

public interface LoginService {

    ResponseCommon loginUser(Map<String, Object> param, String code);

    ResponseCommon getPersonInfo(Map<String, Object> param);

}
