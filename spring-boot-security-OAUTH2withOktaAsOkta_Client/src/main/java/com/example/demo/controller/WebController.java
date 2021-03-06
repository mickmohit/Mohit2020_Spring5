package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.Principal;

@Controller
public class WebController {
	
	/*
	 * Since we associated the Principal with authorized clients, we can obtain the
	 * OAuth2AuthorizedClient instance using the @RegisteredOAuth2AuthorizedClient
	 * annotation
	 */
	
    @RequestMapping("/securedPage")
    public String securedPage(Model model, Principal principal)
    	//	@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,  
         //   @AuthenticationPrincipal OAuth2User oauth2User) {
    		{
    	/*model.addAttribute("userName", oauth2User.getName());  
        model.addAttribute("clientName", authorizedClient.getClientRegistration().getClientName());  
        model.addAttribute("userAttributes", oauth2User.getAttributes()); */ 
    	
        return "securedPage";
    }
    
    @RequestMapping("/")
    public String index(Model model, Principal principal) {
        return "index";
    }
}