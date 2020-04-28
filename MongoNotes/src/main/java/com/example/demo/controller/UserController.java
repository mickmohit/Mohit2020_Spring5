package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.customRepository.DomainRepositoryImpl;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping(value = "/")
public class UserController {
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	private  UserRepository userRepository;
	
	@Autowired
	private  DomainRepositoryImpl domainRepositoryImpl;
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		LOG.info("Getting all users.");
		return userRepository.findAll();
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public Optional<User> getUser(@PathVariable String userId) {
		LOG.info("Getting user with ID: {}.", userId);
		User p = new User ( ) ;
		p.setUserId(userId) ;
		Example <User> example = Example.of ( p ) ;
		//return userRepository.findOne(example);
		return userRepository.findById(userId);
	}
	

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public User addNewUsers(@RequestBody User user) {
		LOG.info("Saving user.");
		return userRepository.save(user);
}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void modifyUserById(@PathVariable("id") String id, @Valid @RequestBody User user) {
	  user.setUserId(id);
	  userRepository.save(user);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable String id) {
		User user=userRepository.findById(id).orElseThrow(null);
		userRepository.delete(user);
	}

	@RequestMapping(value = "/settings/{userId}", method = RequestMethod.GET)
	public Object getAllUserSettings(@PathVariable String userId) {
		
			
		User user = userRepository.findById(userId).orElseThrow(null);
		if (user != null) {
			return user.getUserSettings();
		} else {
			return "User not found.";
		}
}

	

		@RequestMapping(value = "/settings/{userId}/{key}", method = RequestMethod.GET)
		public String getUserSetting(@PathVariable String userId, @PathVariable String key) {
			User user = userRepository.findById(userId).orElseThrow(null);
			if (user != null) {
				return user.getUserSettings().get(key);
			} else {
				return "User not found.";
			}
		}
		

		@RequestMapping(value = "/settings/{userId}/{key}/{value}", method = RequestMethod.GET)
		public String addUserSetting(@PathVariable String userId, @PathVariable String key, @PathVariable String value) {
			User user = userRepository.findById(userId).orElseThrow(null);
			if (user != null) {
				user.getUserSettings().put(key, value);
				userRepository.save(user);
				return "Key added";
			} else {
				return "User not found.";
			}
		}

		//custom repository methods
		
		@RequestMapping(value = "/user/{userName}", method = RequestMethod.GET)
		public List<User> findByName(@PathVariable String userName)
		{
			return userRepository.findByName(userName);
		}

		@RequestMapping(value = "/userJson/{userName}", method = RequestMethod.GET)
		public List<User> findUserByName(@PathVariable String userName)
		{
			return userRepository.findUserByName(userName);
		}
		
		@RequestMapping(value = "/userCustom/{userName}", method = RequestMethod.GET)
		public List<User> findCustomUserByRegExName(@PathVariable String userName)
		{
			return userRepository.findCustomUserByRegExName(userName);
		}
		
		@RequestMapping(value = "/userSettings/{userId}/{key}/{value}", method = RequestMethod.PUT)
		public String updateUser(@PathVariable String userId, @PathVariable String key, @PathVariable String value) {
			//userRepository.updateName(userId, key, value);
			LOG.info("updating user.");
			return domainRepositoryImpl.updateName(userId, key, value);
		}
}
