package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.publisher.CustomerInfoPublisher;

@SpringBootApplication
public class SpringBootRedisPubSubApplication implements CommandLineRunner {

	@Autowired
	  private CustomerInfoPublisher redisPublisher;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootRedisPubSubApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		redisPublisher.publish();
	    redisPublisher.publish();
	    redisPublisher.publish();
	    Thread.sleep(50);
	    redisPublisher.publish();
	    redisPublisher.publish();
	}

}
