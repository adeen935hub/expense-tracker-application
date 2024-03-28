package com.expensemanagement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

import com.expensetracker.config.CustomApplicationContextInitializer;

@SpringBootTest
@ContextConfiguration(initializers = CustomApplicationContextInitializer.class)
class ExpenseTrackerApplicationTests {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	void contextLoads() {
		String originalInput = "admin@123";
		String encodedString = passwordEncoder.encode(originalInput);
		System.out.println(encodedString);
	}

}
