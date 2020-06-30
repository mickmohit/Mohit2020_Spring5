package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.example.demo.DTO.PasswordDto;
import com.example.demo.model.User;
import com.example.demo.service.AmazonService;
import com.example.demo.service.UserService;
import com.example.demo.utils.ErrorUtils;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AmazonService amazonService;
	
	@RequestMapping("/login")
	/*public String login(Model model, String error, String logout)  /// Older way of retreiving user session details from user login details and handling response in controller class
	{
		if(error!=null)
		{
			model.addAttribute("error", "Username or passowrd is invalid");
		}
		if(logout!=null)
		{
			model.addAttribute("message","User has been Logged out");
		}
		
		return "login";
	}*/
	public String login(Model model, HttpSession httpsession) // newer way of handling user session details from user login details and handling response in controller class and also setting error in Spring security class
	{
		Object error = httpsession.getAttribute("error");
		Object logout= httpsession.getAttribute("message");
		
		if(error!=null)
		{
			model.addAttribute("error", error);
			httpsession.removeAttribute("error");
		}
		if(logout!=null)
		{
			model.addAttribute("message",logout);
			httpsession.removeAttribute("message");
		}
		
		return "login";
	}
	
	@GetMapping("/form")
	public String userForm(Model model)
	{
		model.addAttribute("isNew", true);
		model.addAttribute("userForm", new User());
		model.addAttribute("roles",userService.roleList());
		return "user/form";
	}
	
		
	@GetMapping("/edit/{id}")
	public String editUser(@PathVariable Long id, Model model)
	{
		model.addAttribute("isNew", false);
		model.addAttribute("userForm", userService.findOne(id));
		model.addAttribute("roles",userService.roleList());
		return "user/form";
	}
	
	@GetMapping("/refresh")
	public String refreshCache(Model model, Pageable pageable)
	{
		userService.refershCache();
		//model.addAttribute("users", userService.userList());
		Page<User> pages=userService.findAllUserPages(pageable);
		
		/*Below code is for modern way of pagination--start*/
		int current= pages.getNumber()+1;
		int begin= Math.max(1, current-5);
		int end= Math.min(begin+5, pages.getTotalPages());
		
		model.addAttribute("current",current);
		model.addAttribute("begin",begin);
		model.addAttribute("end",end);
		
		/*code end */
		
		model.addAttribute("number", pages.getNumber());
		model.addAttribute("totalPages", pages.getTotalPages());
		model.addAttribute("totalElements", pages.getTotalElements());
		model.addAttribute("size", pages.getSize());
		
		model.addAttribute("users", pages.getContent());
		return "user/list";
	}
	
	
	@GetMapping("/list")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public String getList(Model model, Pageable pageable)
	{
		Page<User> pages=userService.findAllUserPages(pageable);
		
		/*Below code is for modern way of pagination*/
		int current= pages.getNumber()+1;
		int begin= Math.max(1, current-5);
		int end= Math.min(begin+5, pages.getTotalPages());
		
		model.addAttribute("current",current);
		model.addAttribute("begin",begin);
		model.addAttribute("end",end);
		
		model.addAttribute("number", pages.getNumber());
		model.addAttribute("totalPages", pages.getTotalPages());
		model.addAttribute("totalElements", pages.getTotalElements());
		model.addAttribute("size", pages.getSize());
		
		
		model.addAttribute("users", pages.getContent());
		//model.addAttribute("users", userService.userList());//commenting as now Pages will be returned instead of complete list at once
		return "user/list";
	}
	
	
	@GetMapping("/list/{id}")
	public Optional<User> findOneUser(@PathVariable("id") Long id)
	{
		return userService.findOne(id);
	}
	
	// below code is to handle json format value so remove model attribute and response type
	@PostMapping(value="/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes= MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String addUser(@Valid @RequestBody User user, BindingResult result)
	{
		System.out.println("user from register---------------------------------"+user.getFullName());
		if(result.hasErrors()) {
			return ErrorUtils.customErrors(result.getAllErrors());
		} else {
			return userService.addUser(user);
		}
	}
	
	///for user registration from login page as spring dont understand post -application-x-www-form-urlencoded-not-workin
	@PostMapping(value="/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody String addUserRegister(@Valid  User user, BindingResult result)
	{
		System.out.println("user from adduserregister---------------------------------"+user.getFullName());
		if(result.hasErrors()) {
			return ErrorUtils.customErrors(result.getAllErrors());
		} else {
			return userService.addUser(user);
		}
	}
	
	@PostMapping(value="/upload",  consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, 
	        produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody String fileUpload(@RequestPart(value="file") MultipartFile multipartFile, @RequestParam(value="editUserId") Long editUserId) {
		
		return amazonService.uploadFile(multipartFile, userService.findOne(editUserId).orElse(null));
	}
	
	
	// below code is for normal form based add calls
	/*@PostMapping("/add")
	public String addUser(@ModelAttribute User user, Model model)
	{
		String message="";
		if(user==null)
		{
			message=" added";
		}
		message=" updated";
		
		model.addAttribute("message", userService.addUser(user).getUserName()+ message+" Successfully");
		return "message";
	}*/
	
	
	@GetMapping(value="/delete/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String deleteUser(@PathVariable("id") Long id)
	{
		return userService.deleteUser(id);
	}
	
	@GetMapping("/register")
	public String register(Model model, HttpSession httpSession) {
		model.addAttribute("userForm", new User());
		return "register";
	}
	
	@GetMapping("/changePassword/{id}")
	public String changePassword(@PathVariable Long id, Model model) {
		model.addAttribute("passwordForm", new PasswordDto());
		model.addAttribute("userId", id);
		return "/user/changePassword";
	}
	
	//@PostMapping("/savePassword")
	@PostMapping(value="/savePassword", produces = MediaType.APPLICATION_JSON_VALUE, consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public  String savePassword(@Valid PasswordDto passwordDto) {
			
	      userService.changeUserPassword(passwordDto.getUserId(),passwordDto.getNewPassword());
		return "welcome";
	       
	}
	
	/*@GetMapping("/delete/{id}")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String deleteUser(@PathVariable("id") Long id, Model model)
	{
		model.addAttribute("message", userService.deleteUser(id));
		return "message";
	}*/
	
	/*
	 * @PutMapping("/update/{id}") //@PreAuthorize("hasRole('ROLE_ADMIN')") public
	 * User updateUser(@RequestBody User user, @PathVariable Long id) {
	 * 
	 * Optional<User> studentOptional = userService.findOne(id);
	 * 
	 * if (!studentOptional.isPresent()) return null;
	 * 
	 * //user.setUserId(id);
	 * 
	 * userService.addUser(user);
	 * 
	 * return user; }
	 */
}
