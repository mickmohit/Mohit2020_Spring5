package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Role;
import com.example.demo.model.User;

public interface UserService {

	List<User> userList();
	
	Optional<User> findOne(Long id);
	
	String addUser(User user);
	
	String deleteUser(Long id);
	
	/* @Query( value="SELECT * from role ",nativeQuery = true) */
	List<Role> roleList(); 
}
