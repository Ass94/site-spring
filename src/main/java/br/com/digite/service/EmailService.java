package br.com.digite.service;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import br.com.digite.model.Cliente;


public interface EmailService {
	
	void sendOrderConfirmationEmail(Cliente cliente);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendHtmlEmail(MimeMessage msg);
	
	void sendConfirmationHtmlEmail(Cliente cliente);

}
