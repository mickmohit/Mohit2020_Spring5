package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class MajorProjectWalletServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MajorProjectWalletServiceApplication.class, args);
	}

}
