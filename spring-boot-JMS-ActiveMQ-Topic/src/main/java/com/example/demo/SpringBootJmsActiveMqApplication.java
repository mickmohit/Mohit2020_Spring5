package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import com.example.demo.model.Product;

@SpringBootApplication
@EnableJms
public class SpringBootJmsActiveMqApplication {

	public static void main(String[] args) {
	SpringApplication.run(SpringBootJmsActiveMqApplication.class, args);
		}

}
