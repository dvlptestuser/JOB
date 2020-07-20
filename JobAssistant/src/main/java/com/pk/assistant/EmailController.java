package com.pk.assistant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
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

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmailController {


	
	public String resumeFile;
	@GetMapping("/")
	public String loginPage(Map<String, Object> model) {

		return "login";
	}

	@PostMapping("/home")
	public String home(@ModelAttribute UserDetails login, Model model) {
		System.out.println("" + login.getUsername());

		if ("Prakash".equalsIgnoreCase(login.getUsername()) && "Pass@1234".equals(login.getPassword())) {
			
			return "homepage";
		} else {
			model.addAttribute("msg", "Invalid credentials");
			return "login";
		}

	}
	
	@PostMapping("/send")
	public String sendApplication(@ModelAttribute UserDetails userDetails,Model model) {
		try {
			sendEmailNow(userDetails);
			model.addAttribute("result","Your application is on the way.. We wish you all the best!");
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("failure","We regret as we failed to send your application. Please try after sometime.If still problem persists, please write us @dvlptestapi@gmail.com");
		}
		return "result";
	}

	
	@GetMapping("/sendMail")
	public String mailSender(@ModelAttribute UserDetails userDetails,Model model) throws AddressException, MessagingException, IOException {
		sendmail();
		return "result";
	}

	private void sendmail() throws AddressException, MessagingException, IOException {
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

		
		multipart.addBodyPart(attachPart);
		msg.setContent(multipart);
		Transport.send(msg);
	}
	
	private boolean sendEmailNow(UserDetails userDetails) throws Exception {

		String PATTERN="yyyy-MM-dd";
		SimpleDateFormat dateFormat=new SimpleDateFormat();
		dateFormat.applyPattern(PATTERN);
		String todayDate=dateFormat.format(Calendar.getInstance().getTime());
		
		// Recipient's email ID needs to be mentioned.
		String to = userDetails.getReceiversEmail();
		// Sender's email ID needs to be mentioned
		String from = "Prakash Kansurkar<dvlptestapi@gmail.com>";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		// Get the Session object.// and pass
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("dvlptestapi@gmail.com", "Prakash@2019");

			}

		});
		// session.setDebug(true);
		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			
			String subject= "Application for "+userDetails.getDesignation() +" @ " + userDetails.getOrgName();
			// Set Subject: header field
			if(userDetails.getCustomSubject()!=null && !"".equalsIgnoreCase(userDetails.getCustomSubject()) && !"".equalsIgnoreCase(userDetails.getCustomSubject().trim())) {
				subject=userDetails.getCustomSubject();
			}
			message.setSubject(subject);

			Multipart multipart = new MimeMultipart();

			MimeBodyPart attachmentPart = new MimeBodyPart();

			MimeBodyPart textPart = new MimeBodyPart();

			
			
			try {
				
			
				
				//String path = "/" + EmailController.class.getProtectionDomain().getCodeSource().getLocation().getPath()  + "Prakash_Kansurkar_4+.pdf";

			
		        File resumeFile = new File("");
				/*File coverLetterFile = new File(classLoader.getResource("CoverLetter_Prakash_Kansurkar.html").getFile());

				String emailBody = readFileAsString(coverLetterFile);
				emailBody=emailBody.replaceAll("#DATE#", todayDate);
				emailBody=emailBody.replaceAll("#ORGNAME#", userDetails.getOrgName());
				emailBody=emailBody.replaceAll("#RECNAME#",userDetails.getReciversName());
				emailBody=emailBody.replaceAll("#DESIGNATION#",userDetails.getDesignation());
				   */
				attachmentPart.attachFile(resumeFile);
				//textPart.setContent(emailBody, "text/html");
				textPart.setContent("Hi", "text/html; charset=utf-8");

				multipart.addBodyPart(textPart);
				multipart.addBodyPart(attachmentPart);

			} catch (IOException e) {

				e.printStackTrace();

			}

			message.setContent(multipart);

			
			System.out.println("sending.*.."+userDetails);
			ClassLoader cl = ClassLoader.getSystemClassLoader();

	        URL[] urls = ((URLClassLoader)cl).getURLs();

	        for(URL url: urls){
	        	System.out.println("**/*"+url.getFile());
	        }
			
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

		return true;
	
	}
	private  String readFileAsString(File fileName) throws Exception {
		String data = "";
		data = new String(Files.readAllBytes(fileName.toPath()));
		return data;
	}
	
	private void downloadFile() throws MalformedURLException, IOException {
		InputStream inputStream = new URL("http://fightcovid.live/Prakash_Kansurkar_3.8_PT.pdf").openStream();
		FileOutputStream fileOS = new FileOutputStream("/Users/username/Documents/file_name.txt");
		int i = IOUtils.copy(inputStream, fileOS);
	}
}
