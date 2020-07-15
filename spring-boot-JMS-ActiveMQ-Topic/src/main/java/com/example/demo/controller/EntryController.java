package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sender.Sender;

@RestController
public class EntryController {

	@Autowired
    Sender sender;
	
	@GetMapping("/start")
	public void start()
	{
		sender.sendMessage();
	}
}
