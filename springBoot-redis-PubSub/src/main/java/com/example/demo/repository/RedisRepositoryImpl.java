package com.example.demo.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Customer;

@Repository
public class RedisRepositoryImpl implements RedisRepository {

	private RedisTemplate<String, Customer> redisTemplate;
    private HashOperations<String, Integer, Customer> hashOperations;
    
    @Autowired
    public RedisRepositoryImpl(RedisTemplate<String, Customer> redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations=this.redisTemplate.opsForHash();
    }

	@Override
	public Map<Integer, Customer> findAllCustomers() {
		return hashOperations.entries("Customer");
	}

	@Override
	public Customer findById(String id) {
		return (Customer) hashOperations.get("Customer", id);
	}

	@Override
	public void save(Customer customer) {
		
		 hashOperations.put("Customer", customer.getId(), customer);
	}

	@Override
	public void delete(String id) {
		 hashOperations.delete("Customer", id);
	}

	@Override
	public void update(Customer customer) {
		save(customer);
	}


}
