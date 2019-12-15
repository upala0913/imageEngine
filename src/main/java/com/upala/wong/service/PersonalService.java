package com.upala.wong.service;

import java.util.Map;

/*****************************
 *  @author 王鹏
 *  @since 2019/11/29 18:37
 *  @version 0.0.1
 *****************************/
public interface PersonalService {

	Map<String, Object> reName(Map<String, Object> data);

	Map<String, Object> bindMobile(Map<String, Object> data);

	Map<String, Object> getMessage(Map<String, Object> data);

	Map<String, Object> getEmailMessage(Map<String, Object> data);

	Map<String, Object> bindEmail(Map<String, Object> data);

	Map<String, Object> getPerson(Map<String, Object> data);

}
