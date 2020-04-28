package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Gender;
import com.example.demo.entity.User;
import com.example.demo.entity.UserProfile;
import com.example.demo.repository.UserRepository;

@RestController
public class UserController {

	
	@Autowired
	public UserRepository userRepository;

	@GetMapping("/user/{id}")
	public Optional<User> getUserById(@PathVariable("id") int id)
	{
		Optional<User> user = userRepository.findById(id);

		return user;
	}

	@GetMapping("/user")
	public List<User> getAllUsers()
	{
		return userRepository.findAll();
	}
	
	@PostMapping("/user")
	public Optional<User> createUser(@RequestBody User user)
	{
		UserProfile userProfile = new UserProfile();
		userProfile.setAddress1("2nd Cross");
		userProfile.setAddress2("Golf View Road, Kodihalli");
		userProfile.setCity("Bangalore");
		userProfile.setCountry("India");
		
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1992, 7, 21);
		userProfile.setDateOfBirth(dateOfBirth.getTime());
		
		userProfile.setGender(Gender.MALE);
		userProfile.setId( 2);
		userProfile.setPhoneNumber("+91-8197882053");
		userProfile.setState("Karnataka");
		userProfile.setStreet("747");
		userProfile.setZipCode("560008");
		userProfile.setUser(user);
		
		/*
		 * UserProfile userProfile = new UserProfile("+91-8197882053", Gender.MALE,
		 * dateOfBirth.getTime(), "747", "2nd Cross", "Golf View Road, Kodihalli",
		 * "Bangalore", "Karnataka", "India", "560008");
		 */

		
		user.setUserProfile(userProfile);
		
		userRepository.save(user);
		
		return getUserById(user.getId());
	}
	
	@DeleteMapping("/user/{id}")
	public List<User> deletePassport(@PathVariable("id") int id)
	{
		userRepository.deleteById(id);

		return getAllUsers();
	}
	
	@PutMapping("/user/{id}")
    public ResponseEntity <User> updateUser(@PathVariable(value = "id") int id,
        @Valid @RequestBody User userDetails) 
	{
        User user = userRepository.findById(id).orElseThrow(null);
        user.setEmail(userDetails.getEmail());
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }
}
