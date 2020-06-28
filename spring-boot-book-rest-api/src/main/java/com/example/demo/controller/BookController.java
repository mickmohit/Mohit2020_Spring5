package com.example.demo.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@GetMapping
	public ResponseEntity<Collection<Book>> findAll(){
		return new ResponseEntity<>(bookService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Optional<Book>> findById(@PathVariable Long id) {
		return new ResponseEntity<>(bookService.findById(id),HttpStatus.OK);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> save(@RequestBody Book book) {
		System.out.println("boook000000000000000000  "+ book.getAuthor());
		return new ResponseEntity<>(bookService.save(book),HttpStatus.CREATED);
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> update(@RequestBody Book book) {
		Book currentBook = bookService.findById(book.getId()).orElse(null);
		 
        if (currentBook == null) {
             return  ResponseEntity.notFound().build();
        }
 
        currentBook.setAuthor(book.getAuthor());
        currentBook.setCoverPhotoURL(book.getCoverPhotoURL());
        currentBook.setIsbnNumber(book.getIsbnNumber());
        currentBook.setLanguage(book.getLanguage());
        currentBook.setPrice(book.getPrice());
        currentBook.setTitle(book.getTitle());
        return new ResponseEntity<>(bookService.save(currentBook), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) {
		return new ResponseEntity<>(bookService.deleteById(id),HttpStatus.OK);
	}
}
