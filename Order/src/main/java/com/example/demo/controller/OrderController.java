package com.example.demo.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.exception.OrderRepository;
import com.example.demo.exception.ResourceNotFoundException;

import com.example.demo.model.OrderItem;
import com.example.demo.model.Orders;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

	@Autowired
    private OrderRepository orderRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	 @GetMapping("/orders")
	    public List <Orders> getAllOrder() {
	        return orderRepository.findAll();
	    }
	
	 @GetMapping("/order/{id}")
		public ResponseEntity <Orders>  getOrder(@PathVariable("id") Long id) throws ResourceNotFoundException
		{
		
		 Orders order = orderRepository.findById(id)
			            .orElseThrow(() -> new ResourceNotFoundException("order not found for this id :: " + id));
			        return ResponseEntity.ok().body(order);
		}
	
	@GetMapping("/allOrderItem/")
	public List<OrderItem> getAllOrderItems() {
		
		
		 ResponseEntity<List<OrderItem>> res = restTemplate.exchange("http://localhost:8082/api/v1/items",
				HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<OrderItem>>() {});
		return (List<OrderItem>) res.getBody();

	}
	
	
	@PostMapping("/order")
    public Orders createOrder(@Valid @RequestBody Orders order) {
		return orderRepository.save(order);
	}
	
}
