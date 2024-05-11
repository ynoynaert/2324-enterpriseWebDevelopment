package com.springBoot.examenOpdracht;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserDetailsService uds;

	@Autowired
	public void configueGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(uds).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.csrfTokenRepository(new HttpSessionCsrfTokenRepository()))
				.authorizeHttpRequests(requests -> requests.requestMatchers("/login**").permitAll()
						.requestMatchers("/css/**").permitAll().requestMatchers("/fonts/**").permitAll()
						.requestMatchers("/images/**").permitAll().requestMatchers("/403**").permitAll()
						.requestMatchers("/*").hasAnyRole("USER", "ADMIN")
						.requestMatchers("/sports/*/addCompetition").hasRole("ADMIN")
						.requestMatchers("/sports/*/buyTickets/*").hasRole("USER")
						.requestMatchers("/sports/tickets").hasRole("USER")
						.requestMatchers("/sports/*").hasAnyRole("USER", "ADMIN"))
				.formLogin(form -> form.defaultSuccessUrl("/sports", true).loginPage("/login")
						.usernameParameter("username").passwordParameter("password"))
				.exceptionHandling(handling -> handling.accessDeniedPage("/403"));
		return http.build();
	}
}