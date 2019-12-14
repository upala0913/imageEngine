package com.upala.wong.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/*****************************
 *  @author 王鹏
 *  @since 2019/12/12 17:07
 *  @version 0.0.1
 *****************************/

@Component
@Log4j2
public class EmailUtils {

	@Value("${spring.mail.username}")
	private String from;

	@Autowired
	private JavaMailSender mailSender;

	/**
	 * 发送邮件
	 * @param to 收件人
	 * @param subject 主题
	 * @param content 内容
	 */
	public void sendSimpleMail(String to, String subject, String content) throws ExceptionUtils {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(content);
		simpleMailMessage.setFrom(from);
		mailSender.send(simpleMailMessage);
	}

}
