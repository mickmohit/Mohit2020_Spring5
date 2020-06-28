package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.example.demo.DTO.Book;
import com.example.demo.model.User;
import com.example.demo.utils.ErrorUtils;


@Controller
@RequestMapping("book")
public class BookController {

	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/form")
	public String bookForm(Model model)
	{
		model.addAttribute("isNew", true);
		model.addAttribute("bookForm", new Book());
		return "/book/form";
	}
	
	
	@GetMapping("/list")
	public String bookList(Model model, Pageable pageable)
	{
		ResponseEntity<Collection<Book>> response = restTemplate.exchange("http://localhost:8081/rest/books/",
				HttpMethod.GET, null, 
				new ParameterizedTypeReference<Collection<Book>>() {});
		
		model.addAttribute("books", response.getBody());
		return "/book/list";
		
	}
	
	@GetMapping("/edit/{id}")
	public String editBook(@PathVariable Long id, Model model)
	{
		model.addAttribute("isNew", false);
		
		ResponseEntity<Book> response = restTemplate.exchange("http://localhost:8081/rest/books/"+id,
				HttpMethod.GET, null, 
				new ParameterizedTypeReference<Book>() {});
		System.out.println("response.getBody() be==="+response.getBody());
		model.addAttribute("bookForm", response.getBody() );
		System.out.println("response.getBody() af==="+response.getBody().getAuthor());
		return "/book/form";
	}
	
	@PostMapping(value="/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes= MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String addBookRegister(@Valid @RequestBody Book book, BindingResult result) throws JSONException
	{
		if(result.hasErrors()) {
			return ErrorUtils.customErrors(result.getAllErrors());
		} else {
			System.out.println("actual body---------"+book.getAuthor());
			ResponseEntity<Book> response =null;
			HttpEntity<Book> request= new HttpEntity<Book>(book);
			System.out.println("request body-------"+request.getBody().getAuthor());
			JSONObject jsonObject = new JSONObject();
			String message=null;
			System.out.println("book.getId()-------"+book.getId());
			if(book.getId()==null)
			{
			 message="Added";
			 response = restTemplate.exchange("http://localhost:8081/rest/books/",
						HttpMethod.POST, request, 
						new ParameterizedTypeReference<Book>() {});
			}
			else
			{
				message="Updated";
				response = restTemplate.exchange("http://localhost:8081/rest/books/",
						HttpMethod.PUT, request, 
						new ParameterizedTypeReference<Book>() {});
			}
			
			jsonObject.put("status", "success");
			jsonObject.put("message", response.getBody().getTitle()+message);
			return jsonObject.toString();
		}
	}
	
	@GetMapping(value="/delete/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String deleteBook(@PathVariable("id") Long id) throws JSONException
	{
		JSONObject jsonObject = new JSONObject();
		ResponseEntity<Book> response = restTemplate.exchange("http://localhost:8081/rest/books/"+id,
				HttpMethod.DELETE, null, 
				new ParameterizedTypeReference<Book>() {});
		
		Book responseBook = response.getBody();
		if(responseBook!=null)
		{
			jsonObject.put("message", "Book Deleted Sucessfully");
		}
		return jsonObject.toString();
	}
}
