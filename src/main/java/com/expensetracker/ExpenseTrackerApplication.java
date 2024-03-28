package com.expensetracker;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.expensetracker.config.CustomApplicationContextInitializer;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaAuditing
public class ExpenseTrackerApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ExpenseTrackerApplication.class)
				.initializers(new CustomApplicationContextInitializer()).run(args);
	}

}
