package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;

@SpringBootApplication(exclude = JmxAutoConfiguration.class)
public class SpringBootSecurityOauth2withJwtTokenStoreExternalStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityOauth2withJwtTokenStoreExternalStoreApplication.class, args);
	}

}
