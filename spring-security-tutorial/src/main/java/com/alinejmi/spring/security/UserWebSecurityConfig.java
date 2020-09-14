package com.alinejmi.spring.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@Order(2)
public class UserWebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/admin/**", "/update")
		.hasRole("ADMIN")
		.antMatchers("/**")
		.hasRole("USER")
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
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.inMemoryAuthentication()
		.passwordEncoder(new BCryptPasswordEncoder())
		.withUser("user")
			.password("$2a$10$Ponw1y9HLIgx.SzEX0ybG.yurVgjMceZQutpuxE85kiBtjLgunNZu")
			.roles("USER")
		.and()
		.withUser("admin")
			.password("$2a$10$Ponw1y9HLIgx.SzEX0ybG.yurVgjMceZQutpuxE85kiBtjLgunNZu")
			.roles("ADMIN", "USER");
	}

}

