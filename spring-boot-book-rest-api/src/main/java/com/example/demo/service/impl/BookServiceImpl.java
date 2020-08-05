package com.example.demo.service.impl;

import java.util.Collection;
import java.util.Optional;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;
import com.example.demo.exception.BookExceptionHandler;
import com.example.demo.exception.BookNotFoundException;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	
	/*
	 * @Override public Collection<Book> findAll() { return
	 * bookRepository.findAll(); }
	 */
	
	  @Override 
	  public Page<Book> findAll(Pageable pageable) 
	  { 
		  return bookRepository.findAll(pageable); 
	  }
	  
	  @Override 
	  public Page<Book> findAll(Pageable pageable, String searchText) 
	  { 
		  return bookRepository.findAllBooks(pageable,searchText); 
	  }
	 
	@Override
	public Optional<Book> findById(Long id) {
		return bookRepository.findById(id);
	}

	@Override
	public Book save(Book book) {
		System.out.println("book service---"+book.getAuthor());
		return bookRepository.save(book);
	}

	@Override
	public Book update(Book book) {
		
		return bookRepository.save(book);
	}

	@Override
	public String deleteById(Long id) {
		
		JSONObject jsonObject = new JSONObject();
		try {
			bookRepository.deleteById(id);
			jsonObject.put("message", "Book deleted successfully");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

}
