package com.alinejmi.spring.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@Order(1)
public class AdminSpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource datasource;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Bean(name = "passwordEncoder")
	PasswordEncoder getPasswordEncoder() {

		DelegatingPasswordEncoder encoder = (DelegatingPasswordEncoder) PasswordEncoderFactories
				.createDelegatingPasswordEncoder();
		return encoder;

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(datasource).passwordEncoder(passwordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/admin/**", "/update")
		.hasAuthority("ADMIN")
		.antMatchers("/**")
		.hasAnyAuthority("USER", "ADMIN")
		.and()
		.formLogin()
			.loginPage("/login")
			.permitAll()
		.and()  
		      .rememberMe()  
		      .key("rem-me-key")  
		      .rememberMeParameter("remember") // it is name of checkbox at login page  
		      .rememberMeCookieName("rememberlogin") // it is name of the cookie  
		      .tokenValiditySeconds(100) // remember for number of seconds  
		.and()
		.logout()
			.permitAll();
	}

}