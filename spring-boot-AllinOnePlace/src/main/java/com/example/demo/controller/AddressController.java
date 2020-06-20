package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Address;
import com.example.demo.model.User;
import com.example.demo.service.AddressService;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("address")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/form")
	public String addressForm(Model model)
	{
		model.addAttribute("isNew", true);
		model.addAttribute("addressForm", new Address());
		model.addAttribute("users", userService.userList());
		return "address/form";
	}
	
	@GetMapping("/edit/{id}")
	public String editAddress(@PathVariable Long id, Model model)
	{
		model.addAttribute("isNew", false);
		model.addAttribute("addressForm", addressService.fineOne(id));
		model.addAttribute("users", userService.userList());
		return "address/form";
	}
	
	@GetMapping("/list")
	public String addressList(Model model)
	{
		model.addAttribute("addresses",addressService.addressList());
		return "address/list";
	}
	
	@GetMapping("/list/{id}")
	public Optional<Address> findAddress(@PathVariable Long id)
	{
		System.out.println("------------"+id);
		return addressService.fineOne(id);
	}
	
	@PostMapping(value="/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String addAddress(@RequestBody Address address)
	{
		return addressService.addAddress(address);
	}
	
	/*@PostMapping("/add") //below is for normal form based add calls
	public String addAddress(@ModelAttribute Address address, Model model)
	{
		String message="";
		if(address==null)
		{
			message=" added";
		}
		message=" updated";
		
		model.addAttribute("message", addressService.addAddress(address).getUser().getUserName()+" Successfully");
		return "message";
	}*/
	
	@GetMapping(value="/delete/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String deleteAddress(@PathVariable Long id)
	{
		return addressService.delete(id);
	}
	
	/*@GetMapping("/delete/{id}")
	public String deleteAddress(@PathVariable Long id, Model model)
	{
		model.addAttribute("message", addressService.delete(id));
		return "message";
	}*/
	
	/*
	 * @PutMapping("/update/{id}") public Address updateAddress(@RequestBody Address
	 * address, @PathVariable Long id) {
	 * 
	 * Optional<Address> addressOptional = addressService.fineOne(id);
	 * 
	 * if (!addressOptional.isPresent()) return null;
	 * 
	 * //address.setAddressId(id);
	 * 
	 * addressService.addAddress(address);
	 * 
	 * return address; }
	 */
}
