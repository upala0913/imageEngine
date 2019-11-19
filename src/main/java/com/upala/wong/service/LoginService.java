package com.upala.wong.service;

import java.util.Map;

/********************************
 *  @program imageEngine
 *  @author upala
 *  @version 0.0.1
 *  @since 2019-11-07 08:41
 *  @description
 ********************************/

public interface LoginService {

    Map<String, Object> loginUser(Map<String, Object> param, String code);

    Map<String, Object> getPersonInfo(Map<String, Object> param);

}
