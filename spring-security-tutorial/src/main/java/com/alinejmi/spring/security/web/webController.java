package com.alinejmi.spring.security.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class webController {

	@GetMapping("/")
	public String getHomePage() {
		return "home";
	}
	
	@GetMapping("/admin")
	public String getAdminPage() {
		return "home";
	}
	
	@GetMapping("/hello")
	public String getHelloPage() {
		return "hello";
	}
	
	
	@GetMapping("/login")
	public String getLoginPage() {
		return "login";
	}
	
	
	@GetMapping("/update")
	public String getUpdatePage() {
		return "update";
	}
	
	
}
