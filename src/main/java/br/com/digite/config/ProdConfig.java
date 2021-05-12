package br.com.digite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.digite.service.EmailService;
import br.com.digite.service.SmtpEmailService;

@Configuration
@Profile("prod")
public class ProdConfig {
	
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}

}
