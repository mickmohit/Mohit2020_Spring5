package com.example.demo.controller;

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

	@GetMapping("/all")
    public String hello() {
        return "Hello Youtube";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/secured/all")
    public String securedHello() {
        return "Secured Hello";
    }

    @GetMapping("/secured/alternate")
    public String alternate() {
        return "alternate";
    }
    
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


    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(Model model){
    	 ModelAndView modelAndView = new ModelAndView();
    	//model.addAttribute("users", new Users());
    	modelAndView.addObject("users", new Users());
    	 modelAndView.setViewName("registration");
    	System.out.print("Going from 1");
    	LOG.info("Going from 1");
      //  return "registration";
    	return modelAndView;
    }

    @RequestMapping(value = "/registrationProcess", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid @ModelAttribute("users")  Users user,
    		BindingResult bindingResult) {

    	ModelAndView modelAndView = new ModelAndView();
    	System.out.print("entering to 2");
    	LOG.info("entering to 2");
    	
		  Optional<Users> userExists =
		  userService.findUserByUserName(user.getName()); 
		/*
		 * if (userExists != null) { LOG.info("entering to user exists"); bindingResult
		 * .rejectValue("name", "error.user",
		 * "There is already a user registered with the user name provided"); }
		 */
		
		  LOG.info("binding value---",bindingResult.hasErrors());
		  if(bindingResult.hasErrors()) 
		  { 
			  List<FieldError> errors = bindingResult.getFieldErrors(); 
			  for (FieldError error : errors ) 
		  { 
				  LOG.info (error.getObjectName() + " - " + error.getDefaultMessage()); 
		  }
		  LOG.info("entering to 3",bindingResult.getErrorCount());
		  modelAndView.setViewName("registration"); 
		  }
		 
		  else {
			  LOG.info("entering to 4");
			  userService.saveUser(user);
			  modelAndView.addObject("successMessage", "User has been registered successfully");
			//  modelAndView.setViewName("login");
			   } 
		  return modelAndView;
    }

    
}
