package com.upala.wong.common;

/********************************
 *  @program imageEngine
 *  @author upala
 *  @version 0.0.1
 *  @since 2019-11-05 16:12
 *  @description
 ********************************/

public class FinalVarCommon {

    /**
     * map集合初始值
     */
    public final static int MAP_SIZE = 16;

	/**
	 * 短信应用 SDK AppID
 	 */
	public final static int APP_ID = 1400210309;

	/**
	 * 短信应用SD卡
	 */
	public final static String APP_KEY = "7c5a6c3e03f0c51155aa08d7f693ee71";

	/**
	 * 短信模板ID
	 */
	public final static int TEMPLATE_ID = 329251;

	/**
	 * 签名
	 */
	public final static String SMS_SIGN = "小王学JAVA";

	// 返回码
	/**
	 * 成功返回码
	 */
	public final static Integer RESULT_SUCCESS_CODE = 200;

	/**
	 * 错误返回码
	 */
	public final static Integer RESULT_FAIL_CODE = 3426;

	/**
	 * 成功返回true
	 */
	public final static boolean RESULT_SUCCESS_FLAG = true;

	/**
	 * 错误返回false
	 */
	public final static boolean RESULT_FAIL_FLAG = false;

	// 验证码常量
	/**
	 * 短信和邮件验证码
	 */
	public final static Integer MESSAGE_CODE = 6;

	/**
	 * 获取登录验证码
	 */
	public final static Integer LOGIN_CODE_INFO = 4;

}
