package com.example.demo.cotnroller;

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

import com.example.demo.entity.Customer;
import com.example.demo.repository.RedisRepository;



@RestController
public class CustomerController {
	
	 @Autowired
	  RedisRepository redisRepository;
	  
	  @PostMapping
	    public Customer save(@RequestBody Customer customer){
		  redisRepository.save(customer);
	        return customer;
	    }

	    @GetMapping
	    public Map<Integer, Customer> list(){
	        return redisRepository.findAllCustomers();
	    }

	    @Cacheable(value = "Customer", key = "#id")
	    @GetMapping("/{id}")
	    public Customer getCustomer(@PathVariable String id){
	        return redisRepository.findById(id);
	    }

	    @PutMapping
	    public Customer update(@RequestBody Customer customer){
	    	redisRepository.update(customer);
	        return customer;
	    }

	    @DeleteMapping("/{id}")
	    public String deleteCustomer(@PathVariable String id){
	    	redisRepository.delete(id);
	        return id;
	    }
	


}
