package com.example.demo.controller;

import java.awt.PageAttributes.MediaType;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Users;
import com.example.demo.service.UserService;

//@RequestMapping("/rest/hello")
@RestController
public class UserController {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
    private UserService userService;

    @GetMapping({"/", "/home"})
    public ModelAndView welcome() {
    	ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }
    
    @RequestMapping(value={"/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @RequestMapping(value="/signup", method = RequestMethod.GET)
    public ModelAndView registration(Model model){
    	 ModelAndView modelAndView = new ModelAndView();
    	//model.addAttribute("users", new Users());
    	modelAndView.addObject("users", new Users());
    	 modelAndView.setViewName("signup");
    	System.out.print("Going from 1");
    	LOG.info("Going from 1");
      //  return "registration";
    	return modelAndView;
    }

 
  
    @RequestMapping(value= {"/signup"}, method=RequestMethod.POST)
    public ModelAndView createUser(@Valid Users user, BindingResult bindingResult) {
    	
    	LOG.info("Entering to 2");
     ModelAndView model = new ModelAndView();
     Optional<Users> userExists =
   		  userService.findUserByUserName(user.getName()); 
     
     /*if(userExists != null) {
      bindingResult.rejectValue("email", "error.user", "This email already exists!");
     }*/
     if(bindingResult.hasErrors()) {
      model.setViewName("signup");
     } else {
      userService.saveUser(user);
      model.addObject("msg", "User has been registered successfully!");
      model.addObject("user", new Users());
      model.setViewName("signup");
     }
     
     return model;
    }
    
}
