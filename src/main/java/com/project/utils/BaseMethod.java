package com.project.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class BaseMethod {
	
	@Value("${emai.id}")
	private String emailId;
	
	@Value("${email.pass}")
	private String emailPass;


	public void sendMail(String recepient, String subject, String messageBody) {

		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailId, emailPass);
//				return new PasswordAuthentication("noreply.genplex@gmail.com", "vbiz wbvw yoej kxin");
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailId));
//			message.setFrom(new InternetAddress("noreply.genplex@gmail.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject(subject);
			message.setText(messageBody);
			Transport.send(message);
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}
	
	public String password() {
		return "gfhgh";
	}

}
