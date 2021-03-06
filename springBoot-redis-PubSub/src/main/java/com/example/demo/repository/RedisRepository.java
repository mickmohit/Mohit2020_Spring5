package com.example.demo.repository;

import java.util.Map;

import com.example.demo.entity.Customer;

public interface  RedisRepository {
	
	Map<Integer, Customer> findAllCustomers();
	
	Customer findById(String id);
	 
    void save(Customer customer);
 
    void delete(String id);
 
    void update(Customer customer);
}
