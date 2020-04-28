package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.RedisRepository;
import com.example.demo.entity.User;

@Service
public class RedisUserServiceImpl implements RedisUserService {

	
	@Autowired
    RedisRepository redisRepository;
	
	
	@Override
	public Map<String, User> findAllUsers() {
		return redisRepository.findAllUsers();
	}

	@Override
	public User findById(String id) {
		// TODO Auto-generated method stub
		return redisRepository.findById(id);
	}

	@Override
	public void save(User user) {
		redisRepository.save(user);
	}

	@Override
	public void delete(String id) {
		redisRepository.delete(id);
	}

	@Override
	public void update(User user) {
		redisRepository.update(user);
	}

}
