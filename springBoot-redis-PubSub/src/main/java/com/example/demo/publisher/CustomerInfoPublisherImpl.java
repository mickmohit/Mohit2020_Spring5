package com.example.demo.publisher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Customer;
import com.example.demo.repository.RedisRepository;

@Service
public class CustomerInfoPublisherImpl implements CustomerInfoPublisher {

	List<Customer> customers = new ArrayList<>(
			Arrays.asList(new Customer(1, "Jack", "Smith"),
		      new Customer(2, "Adam", "Johnson"), 
		      new Customer(3, "Kim", "Smith"), 
		      new Customer(4, "David", "Williams"),
		      new Customer(5, "Peter", "Davis")));
		 
		  private final AtomicInteger counter = new AtomicInteger(0);
		 
		  @Autowired
		  private RedisTemplate<String, Customer> redisTemplate;
		 
		  @Autowired
		  private ChannelTopic topic;
		  
		
		 
		  public CustomerInfoPublisherImpl() {
		  }
		 
		  public CustomerInfoPublisherImpl(RedisTemplate<String, Customer> redisTemplate, ChannelTopic topic) {
		    this.redisTemplate = redisTemplate;
		    this.topic = topic;
		  }
		 
		  @Override
		  public void publish() {
		    Customer customer = customers.get(counter.getAndIncrement());
		    System.out.println(
		        "Publishing... customer with id=" + customer.getId() + ", " + Thread.currentThread().getName());
		 
		   redisTemplate.convertAndSend(topic.getTopic(), customer.toString());
		  
		  }

}
