package com.example.sqljpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MySqlJpaProjectApplication extends SpringBootServletInitializer{

	@Override 
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MySqlJpaProjectApplication.class);
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(MySqlJpaProjectApplication.class, args);
	}

}
