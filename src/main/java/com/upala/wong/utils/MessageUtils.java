package com.upala.wong.utils;

import com.alibaba.fastjson.JSONException;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import lombok.extern.log4j.Log4j2;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

/*****************************
 *  @author 王鹏
 *  @since 2019/12/10 12:31
 *  @version 0.0.1
 *****************************/

@Log4j2
public class MessageUtils {

	/**
	 * 撒送短信
	 *
	 * @param phoneNumbers 入参
	 * @param con          入参
	 */
	public static SmsSingleSenderResult sendMessage(String[] phoneNumbers, String[] con) {
		SmsSingleSenderResult result = null;
		try {
			SmsSingleSender sender = new SmsSingleSender(FinalVarUtil.APP_ID, FinalVarUtil.APP_KEY);
			result = sender.sendWithParam("86", phoneNumbers[0], FinalVarUtil.TEMPLATE_ID, con,
					FinalVarUtil.SMS_SIGN, "", "");
			log.info("短信验证码：{}", result);
		} catch (HTTPException | JSONException | IOException e) {
			// HTTP 响应码错误/JSON 解析错误/网络 IO 错误
			log.error("错误信息：{}", e.getMessage());
		}
		return result;
	}

	/**
	 * 发送邮件方法
	 * @param toUser 入参
	 * @param content 入参
	 * @return 返回值
	 */
	public static boolean sendMail(String toUser, String content) {
		final Properties proper = new Properties();
		proper.setProperty("mail.transport.protocol", "SMTP");
		proper.setProperty("mail.smtp.host", "smtp.163.com");
		proper.setProperty("mail.smtp.port", "25");
		proper.setProperty("mail.smtp.auth", "true");
		proper.setProperty("mail.smtp.timeout", "1000");
		//1、获取连接，连接到收件人信息
		Session session = Session.getInstance(proper, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("upala178421@163.com", "wp940818");
			}
		});
		//2、创建邮件对象
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("upala178421@163.com")); // 设置发件人的信息
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(toUser)); // 设置收件人信息
			message.setRecipient(Message.RecipientType.CC, new InternetAddress(toUser)); // 设置抄送人信息
			message.setContent(content, "text/text;charset=utf-8");
		} catch (MessagingException e) {
			log.error("创建邮件对象错误", e);
		}
		//3、发送邮件
		try {
			Transport.send(message);
			return true;
		} catch (MessagingException e) {
			log.error("发送邮件错误！", e);
			return false;
		}
	}

}
