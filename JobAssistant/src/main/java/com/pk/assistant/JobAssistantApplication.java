package com.pk.assistant;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JobAssistantApplication {

	public static void main(String[] args) throws AddressException, MessagingException, IOException {
		SpringApplication.run(JobAssistantApplication.class, args);
		sendmail();

	}

	private static void sendmail() throws AddressException, MessagingException, IOException {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("dvlptestapi@gmail.com", "Prakash@2019");
			}
		});
		Message msg = new MimeMessage(session);

		String from = "John Smith<dvlptestapi@gmail.com>";
		msg.setFrom(new InternetAddress(from, false));

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("ptechnophile@gmail.com"));
		msg.setSubject("Tutorials point email");
		msg.setContent("Tutorials point email", "text/html");

		msg.setSentDate(new Date());

		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent("Tutorials point email", "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		MimeBodyPart attachPart = new MimeBodyPart();

		attachPart.attachFile("C:\\Users\\Technophile\\Downloads\\android.png");
		multipart.addBodyPart(attachPart);
		msg.setContent(multipart);
		Transport.send(msg);
	}

}
