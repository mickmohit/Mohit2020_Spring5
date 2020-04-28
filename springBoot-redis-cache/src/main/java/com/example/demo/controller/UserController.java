package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.RedisUserService;

@RestController
public class UserController {

	  @Autowired
	  RedisUserService  redisUserService;
	  
	  @PostMapping
	    public User save(@RequestBody User user){
		  redisUserService.save(user);
	        return user;
	    }

	    @GetMapping
	    public Map<String, User> list(){
	        return redisUserService.findAllUsers();
	    }

	    @Cacheable(value = "USERS", key = "#id")
	    @GetMapping("/{id}")
	    public User getUser(@PathVariable String id){
	        return redisUserService.findById(id);
	    }

	    @PutMapping
	    public User update(@RequestBody User user){
	    	redisUserService.update(user);
	        return user;
	    }

	    @DeleteMapping("/{id}")
	    public String deleteUser(@PathVariable String id){
	    	redisUserService.delete(id);
	        return id;
	    }
	
}
