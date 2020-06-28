package com.example.demo.service;

import java.util.Collection;
import java.util.Optional;

import com.example.demo.entity.Book;

public interface BookService {

	Collection<Book> findAll();
	
	Optional<Book> findById(Long id);
	
	Book save(Book book);
	
	Book update(Book book);
	
	String deleteById(Long id);
	
}
