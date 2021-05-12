package br.com.digite.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.digite.service.DBService;
import br.com.digite.service.EmailService;
import br.com.digite.service.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	private DBService service;
	
	@Bean
	public void iniciar() {
		service.iniciarBancoDados();
	}
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}

}
