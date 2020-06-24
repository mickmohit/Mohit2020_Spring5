package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.ServerMessageConfig;

@Controller
public class WelcomeController {

	@Autowired
	private ServerMessageConfig messageConfig;
	
	@GetMapping("/")
	public String welcome(Model model)
	{
		model.addAttribute("message", messageConfig.getMessage("welcome.heading"));
		return "welcome";
	}
}
