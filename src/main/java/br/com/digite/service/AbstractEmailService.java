package br.com.digite.service;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.digite.model.Cliente;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	@Override
	public void sendOrderConfirmationEmail(Cliente cliente) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromCliente(cliente);
		sendEmail(sm);
	}
	
	protected String htmlFromTemplateCliente(Cliente cliente) {
		Context context = new Context();
		context.setVariable("cliente", cliente);
		return templateEngine.process("email/confirmacao", context);
	}
	
	@Override
	public void sendConfirmationHtmlEmail(Cliente cliente) {
		try {
			MimeMessage mm = prepareMimeMessageFromCliente(cliente);
			sendHtmlEmail(mm);
		} catch(MessagingException e) {
			sendConfirmationHtmlEmail(cliente);
		}
		
	}
	
	protected MimeMessage prepareMimeMessageFromCliente(Cliente cliente) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(cliente.getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Pedido confirmado! Código: " + cliente.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplateCliente(cliente), true);
		return mimeMessage;
	}
	
	protected SimpleMailMessage prepareSimpleMailMessageFromCliente(Cliente cliente) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(cliente.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado! Código: " + cliente.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(cliente.toString());
		return sm;
	}

}
