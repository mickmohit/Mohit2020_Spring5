package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@SpringBootApplication
public class SpringBootFullStackWithReactJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFullStackWithReactJpaApplication.class, args);
	}
	
	

}
