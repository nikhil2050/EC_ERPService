package com.ec.erp.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication
@EnableJpaRepositories("com.ec.erp.*")
@ComponentScan(basePackages = {"com.ec.erp.*"})
@EntityScan("com.ec.erp.*")
public class ERPApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(ERPApplication.class, args);
	}
	
}
