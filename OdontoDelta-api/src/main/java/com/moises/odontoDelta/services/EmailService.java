package com.moises.odontoDelta.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.moises.odontoDelta.domain.Orcamento;

public interface EmailService {

	void sendOrderConfirmationEmail(Orcamento obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Orcamento obj); 
	
	void sendHtmlEmail(MimeMessage msg); 
}
