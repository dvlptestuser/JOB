package com.pk.assistant;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JobAssistantApplication {

	public static void main(String[] args) throws AddressException, MessagingException, IOException {
		SpringApplication.run(JobAssistantApplication.class, args);
		

	}

	
}
