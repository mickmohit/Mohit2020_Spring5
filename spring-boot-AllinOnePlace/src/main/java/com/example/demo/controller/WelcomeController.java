package com.example.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.ServerMessageConfig;
import com.example.demo.model.User;
import com.example.demo.model.Video;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.VideoService;
import com.example.demo.utils.MethodUtils;

@Controller
public class WelcomeController {

	@Autowired
	private ServerMessageConfig messageConfig;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private VideoService videoService;
	
	
	@GetMapping("/")
	public String welcome(Model model)
	{
		model.addAttribute("message", messageConfig.getMessage("welcome.heading"));
			
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String username =null;
		
		if (principal instanceof UserDetails) {
		 username = ((UserDetails)principal).getUsername();
		} else {
		 username = principal.toString();
		}
		
		User user=userRepository.findByUserName(username);
		
		model.addAttribute("user",user);
		return "welcome";
	}
	
	@GetMapping("/home/list")
	public String welcome(Model model, Pageable pageable) {
		Page<Video> pages = videoService.findAll(pageable);
		model.addAttribute("videos", pages.getContent());
		MethodUtils.pageModel(model, pages);
		return "/video/videos";
	}
}
