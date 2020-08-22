package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.OrderItem;
import com.example.demo.repo.OrderItemRepository;

@RestController
@RequestMapping("/api/v1")
public class OrderItemController {

	
	@Autowired
    private OrderItemRepository orderItemRepository;
	
		
	 @GetMapping("/items")
	    public  ResponseEntity<List<OrderItem>> getAllOrderItem() {
	        return ResponseEntity.ok().body(orderItemRepository.findAll());
	    }
	
	@GetMapping("/item/{id}")
	public ResponseEntity <OrderItem>  getOrderItem(@PathVariable("id") String id) throws ResourceNotFoundException
	{
	
		OrderItem orderItem = orderItemRepository.findById(id)
		            .orElseThrow(() -> new ResourceNotFoundException("orderItem not found for this id :: " + id));
		        return ResponseEntity.ok().body(orderItem);
	}
	
	@PostMapping("/item")
    public OrderItem createOrderItem(@Valid @RequestBody OrderItem orderItem) {
           return orderItemRepository.save(orderItem);
    }
}
