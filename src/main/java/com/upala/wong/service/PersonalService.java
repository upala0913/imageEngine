package com.upala.wong.service;

import com.upala.wong.common.ResponseCommon;

import java.util.Map;

/*****************************
 *  @author 王鹏
 *  @since 2019/11/29 18:37
 *  @version 0.0.1
 *****************************/
public interface PersonalService {

	ResponseCommon reName(Map<String, Object> data);

	ResponseCommon bindMobile(Map<String, Object> data);

	ResponseCommon getMessage(Map<String, Object> data);

	ResponseCommon getEmailMessage(Map<String, Object> data);

	ResponseCommon bindEmail(Map<String, Object> data);

	ResponseCommon getPerson(Map<String, Object> data);

}
