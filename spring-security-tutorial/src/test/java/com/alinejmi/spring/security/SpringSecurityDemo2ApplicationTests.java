package com.alinejmi.spring.security;

import static org.mockito.Mockito.CALLS_REAL_METHODS;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SpringSecurityDemo2ApplicationTests {

	@Test
	void contextLoads() {
		String p = new BCryptPasswordEncoder().encode("password");
			System.out.println(p);
	}

}
