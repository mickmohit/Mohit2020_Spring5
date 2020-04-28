package com.example.demo;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entity.Address;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@SpringBootApplication
public class JpaElementCollectionCollectionTableDemoApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaElementCollectionCollectionTableDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		userRepository.deleteAll();

        // Insert a user with multiple phone numbers and addresses.
        Set<String> phoneNumbers = new HashSet<>();
        phoneNumbers.add("+91-9999999999");
        phoneNumbers.add("+91-9898989898");

        Set<Address> addresses = new HashSet<>();
        addresses.add(new Address("747", "Golf View Road", "Bangalore",
                "Karnataka", "India", "560008"));
        addresses.add(new Address("Plot No 44", "Electronic City", "Bangalore",
                "Karnataka", "India", "560001"));

        User user = new User("Mohit Darmwal", "mohit@sita.com",
                phoneNumbers, addresses);

        userRepository.save(user);
		
	}

}
