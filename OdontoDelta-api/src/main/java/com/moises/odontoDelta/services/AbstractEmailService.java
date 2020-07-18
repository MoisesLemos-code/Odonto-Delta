package com.moises.odontoDelta.services;

import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.moises.odontoDelta.domain.Orcamento;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	

	
	@Override
	public void sendOrderConfirmationEmail(Orcamento obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromOrcamento(obj);
	    sendEmail(sm);
	}

	protected  SimpleMailMessage prepareSimpleMailMessageFromOrcamento(Orcamento obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Orcamento confirmado - Codigo "+ obj.getCodigo());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		
		return sm;
	}
	
	protected String htmlFromTemplateOrcamento(Orcamento obj) {
		Context context = new Context();
		context.setVariable("orcamento", obj);
		return templateEngine.process("email/confirmacaoOrcamento", context);
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Orcamento obj) {
		MimeMessage mm = prepareMimeMessageFromOrcamento(obj);
	    sendHtmlEmail(mm);
	}

	private MimeMessage prepareMimeMessageFromOrcamento(Orcamento obj) {
		
		
		return null;
	}
	
}
