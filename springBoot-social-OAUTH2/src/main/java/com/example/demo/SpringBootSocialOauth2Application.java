package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.demo.config.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class SpringBootSocialOauth2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSocialOauth2Application.class, args);
	}

}
