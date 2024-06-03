package com.project.utils;

import java.security.SecureRandom;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class BaseMethod {

	@Value("${emai.id}")
	private String emailId;

	@Value("${email.pass}")
	private String emailPass;

	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static final int PASSWORD_LENGTH = 8;
	private static final SecureRandom random = new SecureRandom();

	public static String getUsername() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user.getUsername();
	}

	public void sendMail(String recepient, String subject, String messageBody) {

		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailId, emailPass);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailId));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject(subject);
			/*message.setText(messageBody);*/
			message.setContent(messageBody, "text/html"); // Set the content type to text/html
			Transport.send(message);
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}

	public String password() {
		StringBuilder password = new StringBuilder(PASSWORD_LENGTH);
		for (int i = 0; i < PASSWORD_LENGTH; i++) {
			password.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
		}
		return password.toString();
	}

}
