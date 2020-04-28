package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.entity.User;

public interface RedisUserService {

	Map<String, User> findAllUsers();
	
	User findById(String id);
	 
    void save(User user);
 
    void delete(String id);
 
    void update(User user);
}
