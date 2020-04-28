package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entity.Address;
import com.example.demo.entity.Name;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@SpringBootApplication
public class JpaEmbeddableEmbeddedDemoApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaEmbeddableEmbeddedDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		 userRepository.deleteAllInBatch();

	        // Insert a new user in the database
	        Name name = new Name("Mohit", "Darmwal", "Singh");
	        Address address = new Address("747", "Golf View Road", "Bangalore", "Karnataka", "India", "560008");
	        User user = new User(name, "mohit@sita.com", address);

	        userRepository.save(user);
		
	}

}
