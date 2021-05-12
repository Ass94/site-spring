package br.com.digite.service;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstractEmailService {
	
	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private JavaMailSender javaMailSender;


	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Enviando e-mail...");
		mailSender.send(msg);
		LOG.info("Email enviado");
		
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Enviando e-mail...");
		javaMailSender.send(msg);
		LOG.info("Email enviado");
		
	}

}
