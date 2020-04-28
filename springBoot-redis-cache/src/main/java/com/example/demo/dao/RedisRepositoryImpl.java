package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public class RedisRepositoryImpl implements RedisRepository{
	
	private RedisTemplate<String, User> redisTemplate;
    private HashOperations<String, String, User> hashOperations;
    
    @Autowired
    public RedisRepositoryImpl(RedisTemplate<String, User> redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations=this.redisTemplate.opsForHash();
    }

	@Override
	public Map<String, User> findAllUsers() {
		return hashOperations.entries("USER");
	}

	@Override
	public User findById(String id) {
		return (User) hashOperations.get("USER", id);
	}

	@Override
	public void save(User user) {
		
		 hashOperations.put("USER", user.getId(), user);
	}

	@Override
	public void delete(String id) {
		 hashOperations.delete("USER", id);
	}

	@Override
	public void update(User user) {
		save(user);
	}

}
