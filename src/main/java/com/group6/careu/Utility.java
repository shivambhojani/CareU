package com.group6.careu;


import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.group6.careu.setting.EmailSetting;

import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

public class Utility {
	public static String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
		
		return siteURL.replace(request.getServletPath(), "");
	}
	
	public static JavaMailSenderImpl prepareMailSender(EmailSetting settings) {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		mailSender.setHost(settings.getMAIL_HOST());
		mailSender.setPort(settings.getMAIL_PORT());
		mailSender.setUsername(settings.getMAIL_USERNAME());
		mailSender.setPassword(settings.getMAIL_PASSWORD());

		Properties mailProperties = new Properties();
		mailProperties.setProperty("mail.smtp.auth", settings.getSMTP_AUTH());
		mailProperties.setProperty("mail.smtp.starttls.enable", settings.getSMTP_SECURED());

		mailSender.setJavaMailProperties(mailProperties);

		return mailSender;
	}
}
