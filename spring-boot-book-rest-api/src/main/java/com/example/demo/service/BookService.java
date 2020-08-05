package com.example.demo.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.Book;

public interface BookService {

	//Collection<Book> findAll(); when you are only using Jpa repo ..with no pagination
	
	Page<Book> findAll(Pageable pagebale);
	
	Page<Book> findAll(Pageable pagebale, String searchText);
	
	Optional<Book> findById(Long id);
	
	Book save(Book book);
	
	Book update(Book book);
	
	String deleteById(Long id);
	
}
