package com.springBoot.examenOpdracht;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import service.MyUserDetailsService;
import service.OlympicService;
import service.OlympicServiceImpl;
import validator.CompetitionValidator;
import validator.TicketValidator;

@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domain")
public class ExamenOpdrachtApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(ExamenOpdrachtApplication.class, args);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/sports");
		registry.addViewController("/403").setViewName("403");
	}

	@Bean
	OlympicService olympicService() {
		return new OlympicServiceImpl();
	}

	@Bean
	LocaleResolver myLocaleResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.ENGLISH);
		return slr;
	}

	@Bean
	DateFormatter dateFormatter() {
		return new DateFormatter();
	}

	@Bean
	UserDetailsService myUserDetailsService() {
		return new MyUserDetailsService();
	}

	@Bean
	CompetitionValidator competitionValidator() {
		return new CompetitionValidator();
	}
	
	@Bean
	TicketValidator ticketValidator() {
		return new TicketValidator();
	}
}
