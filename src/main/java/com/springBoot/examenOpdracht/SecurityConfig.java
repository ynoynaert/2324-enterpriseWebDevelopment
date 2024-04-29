//package com.springBoot.examenOpdracht;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//	@Autowired
//	DataSource dataSource;
//
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		var encoder = new BCryptPasswordEncoder();
//		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(encoder);
//
//	}
//
//	@Bean
//	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.csrf().and()
//				.authorizeHttpRequests(requests -> requests.requestMatchers("/login**").permitAll()
//						.requestMatchers("/css/**").permitAll().requestMatchers("/403**").permitAll()
//						.requestMatchers("/rest/**").permitAll().requestMatchers("/403**").permitAll()
//						.requestMatchers("/bibliotheek/add").hasRole("ADMIN").requestMatchers("/bibliotheek/**")
//						.hasAnyRole("USER", "ADMIN").requestMatchers("/favorieten/**").hasAnyRole("USER", "ADMIN"))
//				.formLogin(form -> form.defaultSuccessUrl("/bibliotheek/list", true).loginPage("/login")
//						.usernameParameter("username").passwordParameter("password"))
//				.exceptionHandling().accessDeniedPage("/403");
//
//		return http.build();
//	}
//
//}