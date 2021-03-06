package com.example.demo;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;

@SpringBootApplication
public class SpringRestSecurityDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestSecurityDemoApplication.class, args);
	}

	 @Bean
	    CommandLineRunner initDatabase(BookRepository repository) {
	        return args -> {
	            repository.save(new Book("A Guide to the Bodhisattva Way of Life", "Mohit", new BigDecimal("15.41")));
	            repository.save(new Book("The Life-Changing Magic of Tidying Up", "Ambrish", new BigDecimal("9.69")));
	            repository.save(new Book("Refactoring: Improving the Design of Existing Code", "Anshul", new BigDecimal("47.99")));
	        };
	    }
}
