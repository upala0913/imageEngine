package com.upala.wong.utils;

/*****************************
 *  @author 王鹏
 *  @since 2019/12/12 17:17
 *  @version 0.0.1
 *****************************/
public class ExceptionUtils extends Exception {

	private String message;

	public ExceptionUtils(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
